package com.joel.factory;

import com.joel.domain.CityQuery;
import com.joel.model.CityRequestModel;
import com.joel.model.Latitude;
import com.joel.model.Longitude;
import com.joel.model.Name;


/**
 * Clase que se encarga de construir
 * un objeto que representa los valores
 * proporcionados por el usuario, con la finalidad
 * de que se haga una consulta con el conjunto
 * de ciudades.
 * 
 * @author Joel Rubio
 *
 */
public class QueryFactory {

	
	/**
	 * @param cityRequest
	 * @return
	 */
	public static CityQuery getQuery(CityRequestModel cityRequest) {
		
		return new CityQuery(new Name(cityRequest.getName()), 
							 new Latitude(cityRequest.getLatitude()),
							 new Longitude(cityRequest.getLongitude()));
	}
}