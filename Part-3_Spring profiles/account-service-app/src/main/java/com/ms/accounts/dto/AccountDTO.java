package com.ms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Account",description = "Schema to hold  Account information")
public class AccountDTO {
	@Schema(description = "Account number",example = "1234567895")
	@NotEmpty
	@Pattern(regexp = "(^$[0-9]{10})", message = "Mobile number must be 10 digits")
	private Long accountNumber;
	@Schema(description = "Account Type",example = "Savings/Current")
	@NotEmpty(message = "Account type can not be null or empty")
	private String accountType;
	@Schema(description = "Branch Address",example = "Delhi")
	@NotEmpty(message = "Account type can not be null or empty")
	private String branchAddress;

}
