package com.ms.cards.exception;

public class CardsAlreadyFoundException extends RuntimeException {
	
	public CardsAlreadyFoundException(String resourceName, String fieldName, String fieldValue){
		super(String.format("%s already found in db %s:'%s'", resourceName, fieldName, fieldValue));
	}

}
