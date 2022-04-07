package com.joel.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Clase que representa los valores
 * de una ciudad que coincide con los
 * parámetros proporcionados por el usuario.
 * 
 * @author Joel Rubio
 *
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CityResponseModel {

	
	private String name;
	private String latitude;
	private String longitude;
	private double score;
}