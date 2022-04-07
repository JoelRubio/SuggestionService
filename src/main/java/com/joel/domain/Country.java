package com.joel.domain;


/**
 * Enumeración que representa un
 * país.
 * 
 * @author Joel Rubio
 *
 */

public enum Country {

	USA,
	Canada,
	Unknown;
	
	private static final String US = "US";
	private static final String CA = "CA";
	
	
	/**
	 * De acuerdo a la abreviatura de un país,
	 * se regresa el nombre de ese país. Si no
	 * se encuentra la abreviatura, se regresa un
	 * valor por defecto.
	 * 
	 * @param countryAbbreviation
	 * @return
	 */
	public static Country getCountry(String countryAbbreviation) {
		
		switch (countryAbbreviation) {
		
			case US:
				return USA;
			
			case CA:
				return Canada;
				
			default:
				return Unknown;
		}
	}
}