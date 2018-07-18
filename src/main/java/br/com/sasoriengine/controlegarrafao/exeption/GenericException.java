package br.com.sasoriengine.controlegarrafao.exeption;

import org.springframework.validation.Errors;

public class GenericException extends RuntimeException {
	
	private Errors errors;
	private String userMessage;

	public GenericException(String message, Errors errors, String userMessage) {
		super(message);
		this.errors = errors;
		this.userMessage = userMessage;
	}

	public GenericException(String message) {
		super(message);
	}
	public Errors getErrors() {
		return errors;
	}

	public String getUserMessage() {
		return userMessage;
	}
}
