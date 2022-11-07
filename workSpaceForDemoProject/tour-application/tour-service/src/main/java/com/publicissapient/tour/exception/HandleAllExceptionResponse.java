package com.publicissapient.tour.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleAllExceptionResponse {

	@ExceptionHandler(PackageExistsException.class)
	public ResponseEntity<String> handlePackageExistsException(PackageExistsException ex) {
		return ResponseEntity.status(404).body(ex.getMessage());
	}

	@ExceptionHandler(UserNameDoesNotExistsException.class)
	public ResponseEntity<String> handleUserNameDoesNotExistsException(UserNameDoesNotExistsException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}
	
	@ExceptionHandler(NotAStaffException.class)
	public ResponseEntity<String> handleNotAStaffExceptionException(NotAStaffException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

	
	
	@ExceptionHandler(BookingExistsException.class)
	public ResponseEntity<String> handleBookingExistsException(BookingExistsException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

	@ExceptionHandler(PackageNotFoundException.class)
	public ResponseEntity<String> handlePackageNotFoundException(PackageNotFoundException e) {
		return ResponseEntity.status(404).body(e.getMessage());
	}

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException ex) {
		return ResponseEntity.status(404).body(ex.getMessage());
	}

	
}
