package com.joel.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.joel.domain.City;
import com.joel.domain.CityQuery;
import com.joel.model.Latitude;
import com.joel.model.Longitude;
import com.joel.model.Name;
import com.joel.service.ScoreService;
import com.joel.utils.GPSUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que se encarga de validar
 * los parámetros proporcionados
 * por el usuario con los attributos
 * de una ciudad (nombre, latitud, longitud).
 * 
 * @author Joel Rubio
 *
 */
@Slf4j
@Service
public class CityScoreService implements ScoreService<City, CityQuery> {

	private static final double MAX_SCORE        = 0.333333333;
	private static final double MAX_DEGREE_SCORE = 0.3;
	private static final double MIN_DEGREE_SCORE = 0.28;
	private static final double HALF_SCORE       = MAX_SCORE / 2.0;
	private static final double MIN_SCORE        = 0.1;
	private static final int MIN_DEGREE          = 1;
	private static final int MAX_DEGREE          = 2;
	
	
	/**
	 * Itera sobre los parámetros dados por el 
	 * usuario y coloca la puntuación.
	 * 
	 * @param city
	 * @param query
	 */
	@Override
	public void apply(City city, CityQuery query) {
		
		log.info("Applying score for each verified city...");
		
		query.getAvailableValues()
			.forEach(value -> {
				
				if (value instanceof Name) {
					
					scoreName(city, (Name) value);
					
				} else if (value instanceof Latitude) {
					
					scoreLatitude(city, (Latitude) value);
					
				} else if (value instanceof Longitude) {
					
					scoreLongitude(city, (Longitude) value);
				}
			});
		
		log.info("Score applied");
	}
	
	
	/**
	 * Coloca la puntuación de acuerdo a la igualdad
	 * que tenga el término dado con el nombre de la
	 * ciudad.
	 * 
	 * @param city
	 * @param name
	 */
	private void scoreName(City city, Name name) {
		
		if (city.getName().toLowerCase().equals(name.getValue().toLowerCase())) {
			
			city.setScore(MAX_SCORE);
		
		}  else if (city.getName().toLowerCase().contains(name.getValue().toLowerCase())) {
		
			city.setScore(HALF_SCORE);
		}
	}
	
	
	/**
	 * Coloca la puntuación a la ciudad de acuerdo
	 * con la precisión de la latitud.
	 * 
	 * @param city
	 * @param latitude
	 */
	private void scoreLatitude(City city, Latitude latitude) {
		
		if (city.getLatitude().equals(latitude.toString())) {
			
			city.setScore(MAX_SCORE);
			
		} else if (GPSUtils.areEqualInIntegerPart(new BigDecimal(city.getLatitude()), latitude.getValue())) {
						
			city.setScore(MAX_DEGREE_SCORE);
			
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLatitude()), latitude.getValue(), 1)) {
			
			city.setScore(MIN_DEGREE_SCORE);
			
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLatitude()), latitude.getValue(), 2)) {
			
			city.setScore(MIN_SCORE);
		}
	}
	
	
	/**
	 * Coloca la puntuación a la ciudad de acuerdo
	 * con la precisión de la longitud. 
	 * 
	 * @param city
	 * @param longitude
	 */
	private void scoreLongitude(City city, Longitude longitude) {
		
		if (city.getLongitude().equals(longitude.toString())) {
			
			city.setScore(MAX_SCORE);
			
		} else if (GPSUtils.areEqualInIntegerPart(new BigDecimal(city.getLongitude()), longitude.getValue())) {
			
			city.setScore(MAX_DEGREE_SCORE);
		
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLongitude()), longitude.getValue(), MIN_DEGREE)) {
		
			city.setScore(MIN_DEGREE_SCORE);
		
		} else if (GPSUtils.differFromSpecificDegree(new BigDecimal(city.getLongitude()), longitude.getValue(), MAX_DEGREE)) {
		
			city.setScore(MIN_SCORE);
		}
	}
}