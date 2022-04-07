package com.joel.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joel.domain.City;
import com.joel.domain.CityQuery;
import com.joel.domain.Country;
import com.joel.model.Latitude;
import com.joel.model.Longitude;
import com.joel.model.Name;


@ExtendWith(MockitoExtension.class)
public class CityScoreServiceTest {

	@Mock
	private CityScoreService scoreService;
	
	@Test
	public void scoreEachCity() {
		
		//given
		String name      = "name1";
		String latitude  = "34.54321";
		String longitude = "90.34543";
		Country country  = Country.USA;
		String code      = "NY";
		double score     = 0.0;
		City city = new City(name, latitude, longitude, country, code, score);
		CityQuery query = new CityQuery(new Name(name), new Latitude(latitude), new Longitude(longitude));
		
		//when
		doNothing().when(scoreService).apply(city, query);
		
		//execute
		scoreService.apply(city, query);
		
		//then
		verify(scoreService, times(1)).apply(city, query);
	}
}