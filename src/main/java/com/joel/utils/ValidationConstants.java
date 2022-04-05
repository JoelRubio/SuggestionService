package com.joel.utils;

import java.math.BigDecimal;

public interface ValidationConstants {

	//Ninguna coordenada (latitud y longitud) real puede tener este valor, 
    //sólo es para tener un valor por defecto si el usuario no ingresó ninguna coordenada.
	BigDecimal DEFAULT_VALUE = BigDecimal.valueOf(1000);
	
	String EMPTY_STR                  = "";
	String LATITUDE_COORDINATE_REGEX  = "(\\+|-)?(?:90(?:(?:\\.0{1,8})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,8})?))";
	String LONGITUDE_COORDINATE_REGEX = "(\\+|-)?(?:180(?:(?:\\.0{1,8})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,8})?))";
}