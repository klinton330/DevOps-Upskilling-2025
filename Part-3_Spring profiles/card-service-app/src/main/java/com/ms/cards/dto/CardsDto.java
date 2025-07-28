package com.ms.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Cards", description = "Schema to hold  Cards information")
public class CardsDto {

	@Schema(description = "Mobile Number", example = "7550127370")
	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;
	@NotEmpty(message = "Card Number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
	@Schema(description = "Card Number of customer", example = "1000765432109")
	private String cardNumber;
	@Schema(description = "Type of the card", example = "Credict/Debit")
	@NotEmpty(message = "CardType can not be a null or empty")
	private String cardType;
	@Positive(message = "Total card limit should be greater than zero")
	@Schema(description = "Total amount limit available against a card", example = "100000")
	private int totalLimit;
	@Schema(description = "Total amount used by a Customer", example = "1000")
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	private int amountUsed;
	@Schema(description = "Total available amount against a card", example = "90000")
	@PositiveOrZero(message = "Total available amount should be equal or greater than zero")
	private int availableAmount;

}
