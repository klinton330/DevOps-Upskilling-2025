package com.ms.cards.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Error Response", description = "Schema to Error response information")
public class ErrorResponse {
	@Schema(description = "API path invoked by client")
	private String apiPath;
	@Schema(description = "Error code representing the error happened")
	private HttpStatus errorCode;
	@Schema(description = "Error message representing the error happened")
	private String errorMessage;
	@Schema(description = "Time representing when the error happened")
	private LocalDateTime errorTime;

}
