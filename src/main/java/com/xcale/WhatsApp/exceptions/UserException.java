package com.xcale.WhatsApp.exceptions;

/**
 * Custom exception to handle a specific error. Used by @ControllerAdvice (CustomControllerAdvice) to send Http response
 * */
public class UserException extends RuntimeException{

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }
    
}
