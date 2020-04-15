package com.assignment.jsoncomparator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Pravin
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	@Override
	public String getMessage() {
		return errorMessage;
	}

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
