package com.jpa.work.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jpa.work.exception.AbstractException;
import com.jpa.work.exception.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(AbstractException.class)
	protected ResponseEntity<?> handleCustomException(AbstractException e) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.code(e.getStatusCode())
			.message(e.getMessage())
			.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.resolve(e.getStatusCode()));
	}
}