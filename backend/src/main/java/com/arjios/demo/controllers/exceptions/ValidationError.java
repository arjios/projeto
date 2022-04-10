package com.arjios.demo.controllers.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessageError> errors = new ArrayList<>();

	public List<FieldMessageError> getErrors() {
		return errors;
	}
		
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessageError(fieldName, message));
	}
	
}
