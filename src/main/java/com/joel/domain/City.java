package com.joel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Clase que representa los
 * valores de una ciudad.
 * 
 * @author Joel Rubio
 *
 */
@Data
@AllArgsConstructor
public class City {
	
	private String name;
	private String latitude;
	private String longitude;
	private Country country;
	private String code;
	private double score;
	
	
	/**
	 * Suma cada nueva puntuación
	 * con la actual.
	 * 
	 * @param score
	 */
	public void setScore(double score) {
		
		this.score += score;
	}
	
	
	/**
	 * Regresa un nuevo objeto de esta
	 * clase con los valores del mismo
	 * objeto. 
	 * 
	 * @return nuevo objeto con los mismo atributos
	 */
	public City clone() {
		
		return new City(this.name, this.latitude, 
					    this.longitude, this.country, 
					    this.code, this.score);
	}
}