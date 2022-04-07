package com.joel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joel.domain.City;
import com.joel.domain.CityQuery;
import com.joel.model.Name;
import com.joel.service.FilterService;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que se encarga de filtrar
 * una lista de ciudades de acuerdo
 * a los parámetros dados por el usuario.
 * 
 * @author Joel Rubio
 *
 */
@Slf4j
@Service
public class CityFilterService implements FilterService<City, CityQuery> {

	
	/**
	 * Filtra una lista de ciudades de acuerdo
	 * a ciertos parámetros.
	 * 
	 * @param cities
	 * @param query
	 * @return si coinciden los párametros con los
	 * 	       elementos de la lista retorna las
	 * 	       ciudades filtradas, si no, retorna una
	 * 		   lista vacía.
	 */
	@Override
	public List<City> filter(List<City> cities, CityQuery query) {
		
		log.info("Filtering cities...");
		
		List<City> matchedCities = cities.parallelStream()
			.filter(city -> verify(city, query))
			.collect(Collectors.toList()); 
		
		log.info("Cities filtered");
		
		return matchedCities.isEmpty() ? List.of() : matchedCities;
	}
	
	/**
	 * Verifica que el término proporcinado por el usuario
	 * sea igual al nombre de la ciudad o se encuentre dentro
	 * de la cadena de caracteres del nombre de la ciudad.
	 * 
	 * @param city
	 * @param query
	 * @return
	 */
	public boolean verify(City city, CityQuery query) {
		
		Name name = (Name) query.getAvailableValues().get(0);
		
		return city.getName().toLowerCase().equals(name.getValue().toLowerCase()) ||
			   city.getName().toLowerCase().contains(name.getValue().toLowerCase());
	}
}