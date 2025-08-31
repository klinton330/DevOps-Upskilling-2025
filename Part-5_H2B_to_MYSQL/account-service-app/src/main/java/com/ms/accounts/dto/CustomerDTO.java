package com.ms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",description = "Schema to hold  Customer information")
public class CustomerDTO {
	
	@Schema(description = "Name of customer",example = "Hari")
	@NotEmpty(message = "Name cannot be Null or empty")
	@Size(min = 5,max=30,message = "The length of the customer name should be between 5 and 30")
	private String name;
	@Schema(description = "Email Id of customer",example = "hari@gmail.com")
	@NotEmpty(message = "Email address cannot be null or empty")
	@Email(message = "Email should be valid value")
	@Column(name = "email")
	private String email;
	@Schema(description = "Mobile No of customer",example = "7550127370")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	@Schema(description = "Account details of Customer")
	private AccountDTO accountDTO;
}
