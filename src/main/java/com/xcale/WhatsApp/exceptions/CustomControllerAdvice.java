package com.xcale.WhatsApp.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ControllerAdvice (CustomControllerAdvice) allows to handle exceptions across the whole application in one global handling component. 
 * It can be viewed as an interceptor of exceptions thrown by methods annotated with @GetMapping and similar.
 * */
@ControllerAdvice
public class CustomControllerAdvice {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorResponse> handleUserException(Exception e) {
		HttpStatus status = HttpStatus.FORBIDDEN;

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), status.value(), status.toString(), e.getMessage(), null, stackTrace, null);
		return new ResponseEntity<>(errorResponse, status);

	}
	
	@ExceptionHandler(ResponseValidException.class)
	public ResponseEntity<ErrorResponse> handleResponseValidException(RuntimeException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), status.value(), status.toString(), null, e.getMessage(), stackTrace, null);
		return new ResponseEntity<>(errorResponse, status);

	}
}
