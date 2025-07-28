package com.ms.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ms.accounts.constants.AccountsConstants;
import com.ms.accounts.dto.AccountDTO;
import com.ms.accounts.dto.CustomerDTO;
import com.ms.accounts.entity.Account;
import com.ms.accounts.entity.Customer;
import com.ms.accounts.exception.CustomerAlreadyExistsException;
import com.ms.accounts.exception.ResourceNotFoundException;
import com.ms.accounts.mapper.AccountsMapper;
import com.ms.accounts.mapper.CustomerMapper;
import com.ms.accounts.repository.AccountRepository;
import com.ms.accounts.repository.CustomerRepository;
import com.ms.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createProduct(CustomerDTO customerDTO) {
		Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer Already registered with given mobileNumber:" + customerDTO.getMobileNumber());
		}
		Customer savedCustomer = customerRepository.save(customer);
		accountRepository.save(createNewAccount(savedCustomer));
	}

	private Account createNewAccount(Customer customer) {
		Account newAccount = new Account();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;
	}

	@Override
	public CustomerDTO fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString()));
		CustomerDTO customerDto = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
		customerDto.setAccountDTO(AccountsMapper.mapToAccountDto(account, new AccountDTO()));
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDTO customerDTO) {
		boolean isUpdated = false;
		AccountDTO accountDTO = customerDTO.getAccountDTO();
		if (accountDTO != null) {
			Account account = accountRepository.findById(accountDTO.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number",
							accountDTO.getAccountNumber().toString()));
			AccountsMapper.maptoAccount(accountDTO, account);
			accountRepository.save(account);
			Long customerId = account.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Id", customerId.toString()));
			CustomerMapper.mapToCustomer(customerDTO, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}
		return isUpdated;

	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		accountRepository.deleteByCustomerId(customer.getCustomerId());;
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}

}
