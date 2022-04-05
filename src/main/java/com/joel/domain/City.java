package com.joel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}