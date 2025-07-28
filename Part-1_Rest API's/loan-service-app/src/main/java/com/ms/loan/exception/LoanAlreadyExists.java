package com.ms.loan.exception;


public class LoanAlreadyExists extends RuntimeException {

	public LoanAlreadyExists(String message) {
		super(message);
	}
}
