package com.joel.utils;

import java.math.BigDecimal;

/**
 * Clase de utilería que se encarga 
 * de validar los valores de una coordenada.
 * 
 * @author Joel Rubio
 *
 */
public class GPSUtils {	
	
	/**
	 * Verifica si las partes enteras de 
	 * dos coordenadas son iguales.
	 * 
	 * @param input1 primera coordenada a comparar
	 * @param input2 segunda coordenada a comparar
	 * 
	 * @return true si las partes enteras son iguales,
	 * 			    false de lo contrario
	 */
	public static boolean areEqualInIntegerPart(BigDecimal input1, BigDecimal input2) {
		
		int input1IntegerPart = input1.intValue();
		int input2IntegerPart = input2.intValue();
		
		return (input1IntegerPart - input2IntegerPart) == 0;
	}
	
	public static boolean differFromSpecificDegree(BigDecimal input1, BigDecimal input2, int degree) {
		
		int input1IntegerPart = input1.intValue();
		int input2IntegerPart = input2.intValue();
		
		return Math.abs((input1IntegerPart - input2IntegerPart)) == degree;
	}
	
	/**
	 * Verifica si los primeros 4 decimales
	 * de dos coordenadas son iguales. 
	 * 
	 * Esto para validar que la distancia entre
	 * coordenadas no es muy grande. Por ejemplo,
	 * 1 en el cuarto decimal de una coordenada 
	 * representa 11.1 metros, y 1 en el quinto decimal 
	 * representa 1.11 metros.
	 * 
	 * 
	 * @param input1 primera coordenada a comparar
	 * @param input2 segunda coordenada a comparar
	 * 
	 * @return true si los primeros 4 decimales son iguales,
	 * 		   false de lo contrario
	 */
	public static boolean areFirst4DecimalsEquals(BigDecimal input1, BigDecimal input2) {
		
		String input1DecimalPart = decimalPartToString(input1);
		String input2DecimalPart = decimalPartToString(input2);
		
		if (input1DecimalPart.length() < 4 || input2DecimalPart.length() < 4) {
			
			return false;
		}
		
		return compare4Decimals(input1DecimalPart, input2DecimalPart);
	}

	/**
	 * Obtiene la parte decimal de una coordenada
	 * y la convierte a String.
	 * 
	 * @param value coordenada de un GPS
	 * 
	 * @return cadena que contiene la parte decimal de una coordenada
	 */
	private static String decimalPartToString(BigDecimal value) {
		
		final String NEGATIVE_SIGN = "-";
		
		String tempValue = value.remainder(BigDecimal.ONE).toString();
		
		return tempValue.contains(NEGATIVE_SIGN) ? tempValue.substring(3) : tempValue.substring(2);	
	}

	/**
	 * Verifica que los primeros 4 decimales de dos
	 * coordenadas sean iguales.
	 * 
	 * @param value1 primera coordenada a comparar en String
	 * @param value2 segunda coordenada a comparar en String
	 * 
	 * @return true si los primeros 4 decimales de dos coordenadas son iguales,
	 * 		   false de lo contrario
	 */
	private static boolean compare4Decimals(String value1, String value2) {
		
		return getFirst4Decimals(value1) == getFirst4Decimals(value2);
	}
	
	/**
	 * Convierte los primeros 4 decimales de una
	 * coordenada a entero.
	 * 
	 * @param value parte decimal de una coordenada
	 * 
	 * @return primeros 4 decimales de una coordenada a entero
	 */
	private static int getFirst4Decimals(String value) {
		
		return Integer.parseInt(value.substring(0, 4));
	}
}