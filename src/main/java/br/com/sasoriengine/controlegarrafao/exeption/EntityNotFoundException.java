package br.com.sasoriengine.controlegarrafao.exeption;

import org.springframework.validation.Errors;

public class EntityNotFoundException extends GenericException{

	public EntityNotFoundException(String message, Errors errors, String userMessage) {
		super(message, errors, userMessage);
	}

	
}
