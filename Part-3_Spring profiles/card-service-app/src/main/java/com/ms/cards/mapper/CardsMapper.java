package com.ms.cards.mapper;

import com.ms.cards.dto.CardsDto;
import com.ms.cards.entity.Cards;

public class CardsMapper {
	public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
		cardsDto.setCardNumber(cards.getCardNumber());
		cardsDto.setCardType(cards.getCardType());
		cardsDto.setMobileNumber(cards.getMobileNumber());
		cardsDto.setTotalLimit(cards.getTotalLimit());
		cardsDto.setAvailableAmount(cards.getAvailableAmount());
		cardsDto.setAmountUsed(cards.getAmountUsed());
		return cardsDto;
	}

	public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
		cards.setCardNumber(cardsDto.getCardNumber());
		cards.setCardType(cardsDto.getCardType());
		cards.setMobileNumber(cardsDto.getMobileNumber());
		cards.setTotalLimit(cardsDto.getTotalLimit());
		cards.setAvailableAmount(cardsDto.getAvailableAmount());
		cards.setAmountUsed(cardsDto.getAmountUsed());
		return cards;
	}
}
