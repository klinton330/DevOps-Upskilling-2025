package com.ms.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.ms.accounts.constants.AccountsConstants;
import com.ms.accounts.dto.CustomerDTO;
import com.ms.accounts.dto.ErrorResponseDTO;
import com.ms.accounts.dto.ResponseDTO;
import com.ms.accounts.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD API for account", description = "Crud Rest API for Account")
@RestController
@RequestMapping(path = "/api/account", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class AccountController {

	private IAccountService accountService;

	@Operation(summary = "Create Account Rest API", description = "Rest API to create new customer and Accounts")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Http Status Created"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@PostMapping
	public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
		accountService.createProduct(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(summary = "Get Account Rest API", description = "Rest API to get details of customer and his/her accounts using mobile Number")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@GetMapping
	public ResponseEntity<CustomerDTO> fetchAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		CustomerDTO customerDTO = accountService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}

	@Operation(summary = "Update Account Rest API", description = "Rest API to update details of customer and his/her accounts ")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "417", description = "Exception Failed"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@PutMapping
	public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO) {
		boolean isUpdated = accountService.updateAccount(customerDTO);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "Delete Account Rest API", description = "Rest API to delete customer and his/her account ")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "No Content"),
			@ApiResponse(responseCode = "417", description = "Exception Failed"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@DeleteMapping
	public ResponseEntity<ResponseDTO> deleteAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		boolean isDeleted = accountService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ResponseDTO(AccountsConstants.STATUS_204, AccountsConstants.MESSAGE_204));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}

}
