package com.joel.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * @author Joel Rubio
 *
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CityResponseModel {

	@SuppressWarnings("unused")
	private String name;
	
	@SuppressWarnings("unused")
	private String latitude;
	
	@SuppressWarnings("unused")
	private String longitude;
	
	@SuppressWarnings("unused")
	private double score;
}
