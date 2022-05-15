package com.xcale.WhatsApp.exceptions;

import java.util.StringJoiner;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Custom exception to handle a specific error. Used by @ControllerAdvice (CustomControllerAdvice) to send Http response
 * */
public class ResponseValidException extends RuntimeException{

    public ResponseValidException() {
        super();
    }

    public ResponseValidException(BindingResult result) {
    	super(formatMessage(result));
    }
    
    private static String formatMessage(BindingResult result){
    	StringJoiner errorsList = new StringJoiner(", ", "{", "}");
    	result.getFieldErrors().stream().forEach(error ->{errorsList.add(error.getField()+"="+error.getDefaultMessage());});
    	ObjectMapper mapper= new ObjectMapper();
    	String jsonString="";
    	try {
			jsonString = mapper.writeValueAsString(errorsList.toString());
		} catch (JsonProcessingException e) {
					e.printStackTrace();
		}
    	return jsonString;
    }
}
