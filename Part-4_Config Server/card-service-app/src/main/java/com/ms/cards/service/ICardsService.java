package com.ms.cards.service;

import com.ms.cards.dto.CardsDto;

public interface ICardsService {

	void createNewCard(String mobileNumber);

	CardsDto fetchCards(String mobileNumber);

	boolean updateCard(CardsDto cardsDto);

	boolean deleteCard(String mobileNumber);

}
