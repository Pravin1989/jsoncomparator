package com.assignment.jsoncomparator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Pravin
 *
 */
@RestControllerAdvice
public class JsonGlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(RuntimeException runtimeException) {
		ResourceNotFoundException cbsErrorMessage = new ResourceNotFoundException(runtimeException.getMessage());
		return new ResponseEntity<>(cbsErrorMessage.getMessage(), HttpStatus.NOT_FOUND);

	}
}
