package com.ms.accounts.service;

import com.ms.accounts.dto.CustomerDTO;

public interface IAccountService {
	void createProduct(CustomerDTO customerDTO);
	CustomerDTO fetchAccount(String mobileNumber);
	boolean updateAccount(CustomerDTO customerDTO);
	boolean deleteAccount(String mobileNumber);
}
