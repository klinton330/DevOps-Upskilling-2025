package com.ms.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ms.cards.constants.CardsConstants;
import com.ms.cards.dto.CardsDto;
import com.ms.cards.entity.Cards;
import com.ms.cards.exception.CardsAlreadyFoundException;
import com.ms.cards.exception.CardsNotFoundException;
import com.ms.cards.mapper.CardsMapper;
import com.ms.cards.repository.CardsRepository;
import com.ms.cards.service.ICardsService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardsService {

	private CardsRepository cardsRepository;

	@Override
	public void createNewCard(String mobileNumber) {

		Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
		if (cards.isPresent())
			throw new CardsAlreadyFoundException("Mobile Number", "Mobile Number", mobileNumber);
		cardsRepository.save(createCard(mobileNumber));

	}

	public Cards createCard(String mobileNumber) {
		Cards cards = new Cards();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		cards.setMobileNumber(mobileNumber);
		cards.setCardNumber(Long.toString(randomCardNumber));
		cards.setCardType(CardsConstants.CREDIT_CARD);
		cards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		cards.setAmountUsed(0);
		cards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
		return cards;
	}

	@Override
	public CardsDto fetchCards(String mobileNumber) {
		Cards card = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new CardsNotFoundException("Mobile No", "Mobile No", mobileNumber));
		return CardsMapper.mapToCardsDto(card, new CardsDto());
	}

	@Override
	public boolean updateCard(CardsDto cardsDto) {
		Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
				.orElseThrow(() -> new CardsNotFoundException("CartNumber", "Cart Number", cardsDto.getCardNumber()));
		Cards needToUpdate = CardsMapper.mapToCards(cardsDto, card);
		cardsRepository.save(needToUpdate);
		return true;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		Cards card = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new CardsNotFoundException("Mobile No", "Mobile No", mobileNumber));
		cardsRepository.deleteById(card.getCardId());
		return true;
	}

}
