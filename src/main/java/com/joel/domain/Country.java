package com.joel.domain;

public enum Country {

	USA,
	Canada,
	Unknown;
	
	public static final String US = "US";
	public static final String CA = "CA";
	
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