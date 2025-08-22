package com.ms.loan.services;

import com.ms.loan.dto.LoansDTO;

public interface ILoanServices {
	
	public void createLoanService(String mobileNumber);
	public LoansDTO fetchLoan(String mobileNumber);
	public boolean updateLoan(LoansDTO loansDTO);
	public boolean deleteLoan(String mobileNumber);

}
