package com.publicissapient.tour.exception;

public class CustomerExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerExistsException(String message) {
		super(message);
	}

}
