package com.joel.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joel.domain.City;
import com.joel.domain.CityQuery;
import com.joel.model.CityResponseModel;
import com.joel.service.FileService;
import com.joel.service.FilterService;
import com.joel.service.ScoreService;
import com.joel.service.SuggestionService;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que contiene la lógica
 * para procesar determinar las
 * sugerencias de acuerdo a los
 * parámetros dados por el usuario
 * y el conjunto de datos disponible.
 * 
 * @author Joel Rubio
 *
 */
@Slf4j
@Service
public class SuggestionServiceImpl implements SuggestionService {
	
	private FileService<City> fileService;
	private FilterService<City, CityQuery> filterService;
	private ScoreService<City, CityQuery> scoreService;
		
	public SuggestionServiceImpl(FileService<City> fileService, 
								 FilterService<City, CityQuery> filterService,
								 ScoreService<City, CityQuery> scoreService) {

		this.fileService   = fileService;
		this.filterService = filterService;
		this.scoreService  = scoreService;
	}

	/**
	 * Obtiene una lista de sugerencias
	 * sobre un conjunto de ciudades a través
	 * del nombre de la ciudad, latitud o longitud.
	 * 
	 * @param suggestionRequest
	 * @return lista de sugerencias de ciudades si hay coincidencias
	 * 		   con los parámetros dados por el usuario, si no, una
	 * 		   lista vacía.
	 */
	@Override
	public Iterable<CityResponseModel> getSuggestion(CityQuery query) {
		
		List<City> cities = fileService.parseFile();
		
		if (cities.isEmpty())
			return List.of();
		
		log.info("Getting suggestions...");
		
		List<City> matchedCities = filterService.filter(cities, query);
		
		Iterable<CityResponseModel> response = buildResponse(matchedCities, query);
		
		log.info("Suggestions obtained");
		
		return response;
	}
	
	
	/**
	 * Itera los elementos de las ciudades seleccionadas,
	 * aplica la puntuación de acuerdo a los parámetros dados,
	 * transforma cada elemento de City a CityResponseModel, y
	 * después los ordena de mayor a menor de acuerdo a su puntuación.
	 * 
	 * @param matchedCities
	 * @param query
	 * @return
	 */
	public Iterable<CityResponseModel> buildResponse(List<City> matchedCities, CityQuery query) {
		
		log.info("Mapping from collection City to collection CityResponseModel...");
		
		List<CityResponseModel> cities = matchedCities.stream()
			.peek(city -> scoreService.apply(city, query))
			.map(city -> CityResponseModel.builder()
					.name(String.format("%s, %s, %s", city.getName(), city.getCode(), city.getCountry().toString()))
					.latitude(city.getLatitude())
					.longitude(city.getLongitude())
					.score(Math.round(city.getScore() * 10.0) / 10.0)
					.build())
			.sorted(Comparator.comparingDouble(CityResponseModel::getScore).reversed())
			.collect(Collectors.toList());
		
		log.info("Mapping finished");
		
		return cities;
	}
}