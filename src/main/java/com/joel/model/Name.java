package com.joel.model;

import com.joel.exception.IllegalQueryException;

import lombok.Getter;
import lombok.Setter;


/**
 * Clase que representa el término
 * a comparar con el nombre de una
 * ciudad.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class Name {
	
	private String value;
	
	public Name(String value) {
		
		validateName(value);
	}
	
	
	/**
	 * @return true si el término está vacío, 
	 * 		   de lo contrario false
	 */
	public boolean isEmpty() {
		
		return value.isEmpty();
	}
	
	
	/**
	 * Valida que el nombre no sea nulo o vacío.
	 * Si es correcto el valor, se asigna a la
	 * variable de instancia.
	 * 
	 * @param value
	 */
	private void validateName(String value) {

		if (value == null || value.isEmpty())
			throw new IllegalQueryException("El término a buscar no puede estar vacío");
		
		this.value = value;
	}
}