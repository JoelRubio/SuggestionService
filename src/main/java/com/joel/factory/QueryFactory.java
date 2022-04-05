package com.joel.factory;

import com.joel.domain.CityQuery;
import com.joel.domain.Latitude;
import com.joel.domain.Longitude;
import com.joel.domain.Name;
import com.joel.model.CityRequestModel;

public class QueryFactory {

	public static CityQuery getQuery(CityRequestModel cityRequest) {
		
		return new CityQuery(new Name(cityRequest.getName()), 
							 new Latitude(cityRequest.getLatitude()),
							 new Longitude(cityRequest.getLongitude()));
	}
}