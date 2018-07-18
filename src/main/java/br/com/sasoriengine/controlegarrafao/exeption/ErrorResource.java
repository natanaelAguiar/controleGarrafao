package br.com.sasoriengine.controlegarrafao.exeption;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {
	private String code;
	private String message;
	private String userMessage;
	private List<FieldErrorResource> fieldErrors;

	public ErrorResource() {
	}

	public ErrorResource(String code, String message, String userMessage) {
		this.code = code;
		this.message = message;
		this.userMessage = userMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUserMessage() {
		return this.userMessage;
	}
	
	public void setUserMessage(String userMessage) { this.userMessage = userMessage; }

	public List<FieldErrorResource> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}