package com.medley.users.exceptions;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")
public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String errorMessage) {
		super(errorMessage);
	}
}