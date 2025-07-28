package com.ms.loan.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ms.loan.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> loanNotFoundException(LoanNotFoundException e, WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
				e.getLocalizedMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
	}

	@ExceptionHandler(LoanAlreadyExists.class)
	public ResponseEntity<ErrorResponseDTO> loanAlreadyExistsException(LoanAlreadyExists e, WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(Exception exception,
			WebRequest webRequest) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(), LocalDateTime.now());
		return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handling Validation Exception
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> validationError = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		validationErrorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationError.put(fieldName, validationMsg);
		});
		return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
	}

}
