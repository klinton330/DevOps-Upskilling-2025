package com.ms.cards.exception;

public class CardsNotFoundException extends RuntimeException {
	
	public CardsNotFoundException(String resourceName, String fieldName, String fieldValue){
		super(String.format("%s not found in db %s:'%s'", resourceName, fieldName, fieldValue));
	}

}
