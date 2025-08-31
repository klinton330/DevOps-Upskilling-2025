package com.ms.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

import com.ms.loan.constants.LoanConstants;
import com.ms.loan.dto.ErrorResponseDTO;
import com.ms.loan.dto.LoanContactInfoDTO;
import com.ms.loan.dto.LoansDTO;
import com.ms.loan.dto.ResponseDTO;
import com.ms.loan.services.ILoanServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
@Tag(name = "CRUD API for loans", description = "Crud Rest API for loans")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/loans", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class LoanController {

	
	private ILoanServices loanService;
	
	@Autowired
	public LoanController(ILoanServices loanService) {
		this.loanService=loanService;
	}
	@Autowired
	private Environment environment;
	
	@Autowired
	private LoanContactInfoDTO loanContactInfoDTO;
	
	@Value("${build.version}")
	private String buildVersion;


	@Operation(summary = "Create Loan Rest API", description = "Rest API to create new Loan for customer")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Http Status Created"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@PostMapping
	public ResponseEntity<ResponseDTO> createLoan(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNo) {
		loanService.createLoanService(mobileNo);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));

	}

	@Operation(summary = "Fetch Loan Rest API", description = "Rest API to fetch Loan for customer")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@GetMapping
	public ResponseEntity<LoansDTO> fetchLaon(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNo) {
		return ResponseEntity.status(HttpStatus.OK).body(loanService.fetchLoan(mobileNo));
	}

	@Operation(summary = "Update Loan Rest API", description = "Rest API to Update Loan for customer")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "417", description = "Exception Failed"),

			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	@PutMapping
	public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoansDTO loansDto) {
		boolean isUpdated = loanService.updateLoan(loansDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "Delete Loan Rest API", description = "Rest API to delete Loan for customer")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "417", description = "Exception Failed"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))

	})

	@DeleteMapping
	public ResponseEntity<ResponseDTO> deleteLoan(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") @RequestParam String mobileNo) {
		boolean isDeleted = loanService.deleteLoan(mobileNo);
		if (isDeleted)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDTO(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
		}

	}
	
	@Operation(summary = "Get Build Information", description = "Get Build Information from this api")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	
	@GetMapping("/build-info")
	public ResponseEntity<String>getBuildInfo(){
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	@Operation(summary = "Get Java Version Information", description = "Get Java version from this api")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))) })
	
	@GetMapping("/java-info")
	public ResponseEntity<String>getJavaVersion(){
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("Path"));
	}
	
	@Operation(summary = "Get contact Information", description = "Get contact info for  api")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status Ok"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal Server error", content = @Content(schema = @Schema(implementation =ErrorResponseDTO.class))) })
	@GetMapping("/contact-info")
	public ResponseEntity<LoanContactInfoDTO>getContactInfo(){
		System.out.println(loanContactInfoDTO);
		return ResponseEntity.status(HttpStatus.OK).body(loanContactInfoDTO);
	}

}
