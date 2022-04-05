package com.joel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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