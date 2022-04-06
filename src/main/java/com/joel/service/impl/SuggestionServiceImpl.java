package com.joel.service.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joel.domain.City;
import com.joel.domain.CityQuery;
import com.joel.domain.Latitude;
import com.joel.domain.Longitude;
import com.joel.domain.Name;
import com.joel.model.CityResponseModel;
import com.joel.service.FileService;
import com.joel.service.SuggestionService;
import com.joel.utils.GPSUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que contiene la lógica
 * para procesar determinar las
 * sugerencias de acuerdo a los
 * parámetros dados por el usuario
 * y el conjunto de datos disponible.
 * 
 * @author joel
 *
 */
@Slf4j
@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Value("${ws.file.path}")
	private String filePath;
	
	private FileService fileService;
		
	public SuggestionServiceImpl(FileService fileService) {

		this.fileService = fileService;
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
		
		
		List<City> cities = this.fileService.parseFile(filePath);
		
		if (cities.isEmpty())
			return List.of();
		
		log.info("Scanning cities...");
		
		List<City> matchedCities = getMatchCities(cities, query);
		
		log.info("Cities scanned");
		
		if (matchedCities.isEmpty())
			return List.of();
		
		
		log.info("Assembling content");
		
		List<CityResponseModel> response = buildResponse(matchedCities);
		
		log.info("Finish assembling content");
		
		return response;
	}
	
	private List<City> getMatchCities(List<City> cities, CityQuery query) {
		
		return cities.parallelStream()
			.filter(city -> filterCity(city, query))
			.collect(Collectors.toList());
	}
	
	private boolean validateName(City city, Name name) {
		
		if (city.getName().equals(name.getValue())) {
			
			city.setScore(0.333333333);
			
			return true;
		} 
		
		if (city.getName().toLowerCase().contains(name.getValue().toLowerCase())) {
		
			city.setScore(0.333333333/2.0);
			
			return true;
		}
		
		return false;
	}
	
//	0.333333333 = equal
//
//	0.3 = equal in integer part
//
//	0.28 = differs from one to two degree
//
//	0.1 = differs from more than two degrees
	
	private void validateLatitude(City city, Latitude latitude) {
		
		if (city.getLatitude().equals(latitude.toString())) {
			
			city.setScore(0.333333333);
			
		} else if (GPSUtils.areEqualInIntegerPart(new BigDecimal(city.getLatitude()), latitude.getValue())) {
						
			city.setScore(0.3);
			
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLatitude()), latitude.getValue(), 1)) {
			
			city.setScore(0.28);
			
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLatitude()), latitude.getValue(), 2)) {
			
			city.setScore(0.1);
		}
	}
	
	private void validateLongitude(City city, Longitude longitude) {
		
		if (city.getLongitude().equals(longitude.toString())) {
			
			city.setScore(0.333333333);
			
		} else if (GPSUtils.areEqualInIntegerPart(new BigDecimal(city.getLongitude()), longitude.getValue())) {
			
			city.setScore(0.3);
		
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLongitude()), longitude.getValue(), 1)) {
		
			city.setScore(0.28);
		
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLongitude()), longitude.getValue(), 2)) {
		
			city.setScore(0.1);
		}
	}
	
	private boolean filterCity(City city, CityQuery query) {
		
		for (Object value : query.getAvailableValues()) {
			
			if (value instanceof Name) {
				
				if (!validateName(city, (Name) value))
					return false;
				
			} else if (value instanceof Latitude) {
				
				validateLatitude(city, (Latitude) value);
				
			} else if (value instanceof Longitude) {
				
				validateLongitude(city, (Longitude) value);
			}
		}
		
		return true;
	}
	
	private List<CityResponseModel> buildResponse(List<City> matchedCities) {
		
		return matchedCities.stream()
			.map(city -> CityResponseModel.builder()
					.name(String.format("%s, %s, %s", city.getName(), city.getCode(), city.getCountry().toString()))
					.latitude(city.getLatitude())
					.longitude(city.getLongitude())
					.score(Math.round(city.getScore() * 10.0) / 10.0)
					.build())
			.sorted(Comparator.comparingDouble(CityResponseModel::getScore).reversed())
			.collect(Collectors.toList());
	}
}