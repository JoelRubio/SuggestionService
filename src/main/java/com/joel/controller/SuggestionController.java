package com.joel.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joel.domain.CityQuery;
import com.joel.exception.IllegalQueryException;
import com.joel.factory.QueryFactory;
import com.joel.model.CityRequestModel;
import com.joel.model.CityResponseModel;
import com.joel.service.SuggestionService;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que se encarga de 
 * procesar una petición HTTP,
 * la cual retornará una lista
 * de sugerencias de ciudades
 * dado un conjunto de parámetros.
 * 
 * @author Joel Rubio
 *
 */
@Slf4j
@RestController
@RequestMapping("/${ws.endpoint}")
public class SuggestionController {

	private SuggestionService suggestionService;
	
	public SuggestionController(SuggestionService suggestionService) {
		
		this.suggestionService = suggestionService;
	}

	/**
	 * Recibe la petición HTTP, la procesa
	 * y responderá con una lista de sugerencias
	 * de ciudades acorde a los parámetros.
	 * 
	 * @param suggestionRequest
	 * @return representación de una respuesta HTTP con el 
	 * 		   código 200, y una lista de sugerencias de ciudades 
	 * 		   si hay coincidencias con los parámetros dados por el 
	 * 		   usuario, si no, una lista vacía.
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Map<String, Iterable<CityResponseModel>> get(CityRequestModel cityRequest) {
		
		log.info("Request: {}", cityRequest.toString());
		
		if (cityRequest.isEmpty())
			throw new IllegalQueryException("La consulta no puede estar vacía");
		
		CityQuery query = QueryFactory.getQuery(cityRequest);
		
		return Map.of("suggestions", suggestionService.getSuggestion(query));
	}
}