package com.joel.model;

import java.math.BigDecimal;

import com.joel.exception.IllegalQueryException;
import com.joel.utils.ValidationConstants;

import lombok.Getter;
import lombok.Setter;


/**
 * Clase que representa la
 * latitud del GPS.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@Setter
public class Latitude {

	private static final String ERROR_MESSAGE = "La latitud sólo puede tener números positivos o negativos entre -90.00000000 y 90.00000000";
	
	private BigDecimal value;
	
	public Latitude(String value) {
		
		validateLatitude(value);
	}
	
	/**
	 * Verifica si la latitud está vacía.
	 * 
	 * @return true si la latitud está vacía (DEFAULT_VALUE),
	 * 		   false de lo contrario
	 */
	public boolean isEmpty() {
		
		return ValidationConstants.DEFAULT_VALUE.equals(value);
	}
	
	/**
	 * Obtiene la representación en cadena
	 * de la latitud.
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		
		return value.toString();
	}
	
	/**
	 * Realiza la validación correspondiente para la latitud
	 * proporcionada por el usuario.
	 * 
	 * @param latitude
	 */
	private void validateLatitude(String latitude) {
		
		if (latitude == null) {
			
			this.value = ValidationConstants.DEFAULT_VALUE;
			
			return;
		}
		
		validateLatitudeRegex(latitude);
		
		this.value = new BigDecimal(latitude);
	}
	
	
	/**
	 * Valida que la latitud sea correcta, de lo contrarios,
	 * arroja una expción.
	 * 
	 * @param latitude
	 */
	private void validateLatitudeRegex(String latitude) {
		
		if (!latitude.matches(ValidationConstants.LATITUDE_COORDINATE_REGEX)) {
			
			throw new IllegalQueryException(ERROR_MESSAGE);
		}
	}
}