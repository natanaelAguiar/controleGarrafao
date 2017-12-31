package br.com.sasoriengine.controlegarrafao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sasoriengine.controlegarrafao.exeption.EntityNotFoundException;
import br.com.sasoriengine.controlegarrafao.exeption.ErrorResource;
import br.com.sasoriengine.controlegarrafao.exeption.FieldErrorResource;
import br.com.sasoriengine.controlegarrafao.exeption.InvalidRequestException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ InvalidRequestException.class })
	protected ResponseEntity<Object> InvalidRequestException(RuntimeException e, WebRequest request) {
		InvalidRequestException ire = (InvalidRequestException) e;
		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

		List<FieldError> fieldErrors;
		if (ire.getErrors() != null) {
			fieldErrors = ire.getErrors().getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				FieldErrorResource fieldErrorResource = new FieldErrorResource();
				fieldErrorResource.setResource(fieldError.getObjectName());
				fieldErrorResource.setField(fieldError.getField());
				fieldErrorResource.setCode(fieldError.getCode());
				fieldErrorResource.setMessage(fieldError.getDefaultMessage());
				fieldErrorResources.add(fieldErrorResource);
			}
		}

		ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage(), ire.getUserMessage());
		error.setFieldErrors(fieldErrorResources);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

	@ExceptionHandler({ EntityNotFoundException.class })
	protected ResponseEntity<Object> handleObjectNotFound(RuntimeException e, WebRequest request) {
		EntityNotFoundException entityNotFoundException = (EntityNotFoundException) e;
		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

		ErrorResource error = new ErrorResource(entityNotFoundException.getClass().getSimpleName(), entityNotFoundException.getMessage(), entityNotFoundException.getUserMessage());
		error.setFieldErrors(fieldErrorResources);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({AuthenticationException.class})
	protected ResponseEntity<Object> handleBadCredentialsException(RuntimeException e, WebRequest request) {
		AuthenticationException authenticationException = (AuthenticationException) e;
		
		ErrorResource error = new ErrorResource(e.getClass().getSimpleName(), authenticationException.getMessage(), authenticationException.getLocalizedMessage());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return handleExceptionInternal(e, error, headers, HttpStatus.UNAUTHORIZED, request);
	}
	
	@ExceptionHandler({AccessDeniedException.class})
	protected ResponseEntity<Object> handleAccessDeniedException(RuntimeException e, WebRequest request) {
		AccessDeniedException accessDeniedException = (AccessDeniedException) e;
		ErrorResource error = new ErrorResource(e.getClass().getSimpleName(), accessDeniedException.getMessage(),null);
		error.setUserMessage("Você não tem permissão para executar esta tarefa");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return handleExceptionInternal(e, error, headers, HttpStatus.FORBIDDEN, request);
	}
}
