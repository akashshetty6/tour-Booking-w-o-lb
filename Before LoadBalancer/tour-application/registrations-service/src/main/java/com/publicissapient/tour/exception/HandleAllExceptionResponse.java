package com.publicissapient.tour.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleAllExceptionResponse  {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerExistsException(CustomerNotFoundException ex){
		return ResponseEntity.status(404).body(ex.getMessage());
	}

	@ExceptionHandler(CustomerExistsException.class)
	public ResponseEntity<String> handleCustomerExistsException(CustomerExistsException ex){
		return ResponseEntity.status(404).body(ex.getMessage());
	}
}
