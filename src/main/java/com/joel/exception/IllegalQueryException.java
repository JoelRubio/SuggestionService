package com.joel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Clase que representa una excepción
 * cuando los valores proporcionados por
 * el usuario son incorrectos. Esta excepción
 * regresa un código HTTP 400 Bad Request.
 * 
 * @author Joel Rubio
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IllegalQueryException extends RuntimeException {

	private static final long serialVersionUID = -834336118779359179L;
	
	public IllegalQueryException() {
		super();
	}
	
	public IllegalQueryException(String message) {
		super(message);
	}
}