package com.ms.accounts.mapper;

import com.ms.accounts.dto.AccountDTO;
import com.ms.accounts.entity.Account;

public class AccountsMapper {

	public static AccountDTO mapToAccountDto(Account accounts, AccountDTO accountDTO) {
		accountDTO.setAccountNumber(accounts.getAccountNumber());
		accountDTO.setAccountType(accounts.getAccountType());
		accountDTO.setBranchAddress(accounts.getBranchAddress());
		return accountDTO;
	}

	public static Account maptoAccount(AccountDTO accountDTO,Account account) {
		account.setAccountNumber(accountDTO.getAccountNumber());
		account.setAccountType(accountDTO.getAccountType());
		account.setBranchAddress(accountDTO.getBranchAddress());
		return account;
	}

}
