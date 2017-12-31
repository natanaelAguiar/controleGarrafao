package br.com.sasoriengine.controlegarrafao.exeption;

import org.springframework.validation.Errors;

public class InvalidRequestException extends GenericException{

	public InvalidRequestException(String message, Errors errors, String userMessage) {
		super(message, errors, userMessage);
	}
	
}
