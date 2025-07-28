package com.ms.loan.exception;

public class LoanNotFoundException extends RuntimeException {

	public LoanNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with given input Value %s:'%s'", resourceName, fieldName, fieldValue));
	}
}
