package com.joel.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

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
public class CityFilterServiceTest {

	@Mock
	private CityFilterService filterService;
	
	@Test
	public void filterListOfCities() {
		
		//given
		String name         = "name1";
		String latitude     = "65.54321";
		String longitude    = "120.34543";
		Country country     = Country.USA;
		String code         = "NY";
		double score        = 0.0;
		CityQuery query     = new CityQuery(new Name(name), new Latitude(latitude), new Longitude(longitude));
		City city1          = new City(name, latitude, longitude, country, code, score);
		City city2          = new City("name2", "70.54321", "120.34543", Country.USA, "MD", 0.0);
		List<City> cities   = List.of(city1, city2);
		List<City> expected = List.of(city1);
		List<City> actualResponse;
		
		//when
		when(filterService.filter(cities, query)).thenReturn(expected);
		
		//execute
		actualResponse = filterService.filter(cities, query);
		
		//then
		assertThat(actualResponse).isEqualTo(expected);
	}
}