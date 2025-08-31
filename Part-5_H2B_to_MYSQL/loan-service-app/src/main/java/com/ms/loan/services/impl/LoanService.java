package com.ms.loan.services.impl;

import java.util.Optional;	
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ms.loan.constants.LoanConstants;
import com.ms.loan.dto.LoansDTO;
import com.ms.loan.entity.Loans;
import com.ms.loan.exception.LoanAlreadyExists;
import com.ms.loan.exception.LoanNotFoundException;
import com.ms.loan.mapper.LoanMapper;
import com.ms.loan.repository.LoanRepository;
import com.ms.loan.services.ILoanServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LoanService implements ILoanServices {

	private LoanRepository loanRepository;

	@Override
	public void createLoanService(String mobileNumber) {

		Optional<Loans> optional = loanRepository.findByMobileNumber(mobileNumber);
		if (optional.isPresent()) {
			new LoanAlreadyExists("Loan already registered with given mobileNumber " + mobileNumber);
		}
		loanRepository.save(createNewLoan(mobileNumber));

	}

	private Loans createNewLoan(String mobileNumber) {
		Loans newLoan = new Loans();
		long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		newLoan.setLoanNumber(Long.toString(randomLoanNumber));
		newLoan.setMobileNumber(mobileNumber);
		newLoan.setLoanType(LoanConstants.HOME_LOAN);
		newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
		newLoan.setAmountPaid(0);
		newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
		return newLoan;
	}

	@Override
	public LoansDTO fetchLoan(String mobileNumber) {
		Loans loan = loanRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new LoanNotFoundException("Loans", "Mobile Number", mobileNumber));
		return LoanMapper.mapToLoansDto(loan, new LoansDTO());
	}

	@Override
	public boolean updateLoan(LoansDTO loansDTO) {
		Loans fromDBloan = loanRepository.findByLoanNumber(loansDTO.getLoanNumber())
				.orElseThrow(() -> new LoanNotFoundException("Loans", "Loan Number", loansDTO.getLoanNumber()));
		LoanMapper.mapToLoans(loansDTO, fromDBloan);
		loanRepository.save(fromDBloan);
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loan = loanRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new LoanNotFoundException("Loans", "Mobile Number", mobileNumber));
		loanRepository.deleteById(loan.getLoanId());
		return true;
	}

}
