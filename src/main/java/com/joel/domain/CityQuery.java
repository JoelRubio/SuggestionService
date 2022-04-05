package com.joel.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CityQuery {

	private Name name;
	private Latitude latitude;	
	private Longitude longitude;
	
	private List<Object> availableValues;
	
	public CityQuery(Name name, Latitude latitude, Longitude longitude) {
		
		this.name      = name;
		this.latitude  = latitude;
		this.longitude = longitude;
		
		availableValues = new ArrayList<>();
		
		setAvailableValues();
	}
	
	private void setAvailableValues() {
		
		if (!name.isEmpty())
			availableValues.add(name);
		
		if (!latitude.isEmpty())
			availableValues.add(latitude);
		
		if (!longitude.isEmpty())
			availableValues.add(longitude);
	}
}