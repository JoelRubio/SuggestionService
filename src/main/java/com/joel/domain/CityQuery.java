package com.joel.domain;

import java.util.ArrayList;
import java.util.List;

import com.joel.model.Latitude;
import com.joel.model.Longitude;
import com.joel.model.Name;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase que representa los valores
 * que se ocuparán para comparar con
 * los elementos de una ciudad.
 * 
 * @author Joel Rubio
 *
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
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
	
	/**
	 * Agrega los elementos proporcionados
	 * por el usuario que no estén vacíos,
	 * para su posterior comparación.
	 */
	private void setAvailableValues() {
		
		if (!name.isEmpty())
			availableValues.add(name);
		
		if (!latitude.isEmpty())
			availableValues.add(latitude);
		
		if (!longitude.isEmpty())
			availableValues.add(longitude);
	}
}