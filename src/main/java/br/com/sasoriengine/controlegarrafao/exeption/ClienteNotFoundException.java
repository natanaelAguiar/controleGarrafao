package br.com.sasoriengine.controlegarrafao.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "This cliente is not found in the system")
public class ClienteNotFoundException extends Exception{
	private static final long serialVersionUID = 100L;
}
