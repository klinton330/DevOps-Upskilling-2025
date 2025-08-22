package com.ms.loan.exception;


public class LoanAlreadyExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanAlreadyExists(String message) {
		super(message);
	}
}
