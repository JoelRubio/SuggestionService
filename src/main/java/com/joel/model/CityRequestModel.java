package com.joel.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Clase que representa los valores
 * que proporciona el usuario para
 * realizar la búsqueda de una ciudad.
 * 
 * @author Joel Rubio
 *
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class CityRequestModel implements Serializable {

	private static final long serialVersionUID = -3061995352129800623L;

	private String name;
	private String latitude;
	private String longitude;
	
	public void setQ(String q) {
		
		this.name = q;
	}
	
	/**
	 * @return
	 */
	public boolean isEmpty() {
		
		return name == null && latitude == null && longitude == null;
	}
}