package com.joel.domain;

import com.joel.exception.IllegalQueryException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Name {
	
	private String value;
	
	public Name(String value) {
		
		validateName(value);
	}
	
	
	public boolean isEmpty() {
		
		return value.isEmpty();
	}
	
	private void validateName(String value) {

		if (value == null || value.isEmpty())
			throw new IllegalQueryException("El término a buscar no puede estar vacío");
		
		this.value = value;
	}
}