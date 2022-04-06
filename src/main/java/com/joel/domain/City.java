package com.joel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
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
	
	public void setScore(double score) {
		
		this.score += score;
	}
	
	/**
	 *
	 */
	public City clone() {
		
		City clonedCity = new City(this.name, this.latitude, 
								  this.longitude, this.country, 
								  this.code, this.score);
		
		return clonedCity;
	}
}