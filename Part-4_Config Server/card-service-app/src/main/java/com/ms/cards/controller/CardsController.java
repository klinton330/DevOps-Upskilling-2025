package com.ms.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.cards.constants.CardsConstants;
import com.ms.cards.dto.CardContactInfoDTO;
import com.ms.cards.dto.CardsDto;
import com.ms.cards.dto.ErrorResponse;
import com.ms.cards.dto.ResponseDTO;
import com.ms.cards.service.ICardsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cards")
@Tag(name = "CRUD API for cards", description = "This RestAPI for perform CARD CRUD operations")
@Validated
public class CardsController {

	
	private ICardsService iCardsService;
	@Autowired
	public CardsController(ICardsService iCardsService) {
		this.iCardsService=iCardsService;
	}
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CardContactInfoDTO cardContactInfoDTO;
	
	
	@Value("${build.version}")
	private String buildVersion;

	@Operation(summary = "Rest API for Create new Cards", description = "This API helps to create new Cards for customers")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Card Created Successfully"),
			@ApiResponse(responseCode = "500", description = "internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@PostMapping
	public ResponseEntity<ResponseDTO> createCard(
			@Valid @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNumber) {
		iCardsService.createNewCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}

	@Operation(summary = "Rest API for fetch cards", description = "This API used to fetch cards using  customer Mobile number")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status is 200"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping
	public ResponseEntity<CardsDto> fetchCardsDTO(
			@Valid @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNumber) {
		CardsDto cardsDto = iCardsService.fetchCards(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
	}

	@Operation(summary = "Rest API for Update cards", description = "This API used to update cards ")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status is 200"),
			@ApiResponse(responseCode = "417", description = "Http Status is 417"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@PutMapping
	public ResponseEntity<ResponseDTO> updateCards(@Valid @RequestBody CardsDto cardsDto) {
		boolean isUpdated = iCardsService.updateCard(cardsDto);
		if (isUpdated)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
		else
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));

	}

	@Operation(summary = "Rest API for Delete cards", description = "This API used to delete cards using  customer Mobile number")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status is 200"),
			@ApiResponse(responseCode = "417", description = "Http Status is 417"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@DeleteMapping
	public ResponseEntity<ResponseDTO> deleteCards(
			@Valid @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNumber) {
		boolean isDeleted = iCardsService.deleteCard(mobileNumber);
		if (isDeleted)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
		else
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));

	}
	
	@Operation(summary = "Get Build Information", description = "Get Build Information from this api")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	
	@GetMapping("/build-info")
	public ResponseEntity<String>getBuildInfo(){
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	@Operation(summary = "Get Java Version Information", description = "Get Java version from this api")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	
	@GetMapping("/java-info")
	public ResponseEntity<String>getJavaVersion(){
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("Path"));
	}
	
	@Operation(summary = "Get contact Information", description = "Get contact info for  api")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping("/contact-info")
	public ResponseEntity<CardContactInfoDTO>getContactInfo(){
		System.out.println(cardContactInfoDTO);
		return ResponseEntity.status(HttpStatus.OK).body(cardContactInfoDTO);
	}

}
