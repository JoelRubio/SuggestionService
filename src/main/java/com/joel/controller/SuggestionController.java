package com.joel.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joel.model.SuggestionRequestModel;
import com.joel.model.SuggestionResponseModel;
import com.joel.service.SuggestionService;


/**
 * Clase que se encarga de 
 * procesar una petición HTTP,
 * la cual retornará una lista
 * de sugerencias de ciudades
 * dado un conjunto de parámetros.
 * 
 * @author joel
 *
 */
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
	public ResponseEntity<List<SuggestionResponseModel>> get(SuggestionRequestModel suggestionRequest) {
		
		List<SuggestionResponseModel> response = this.suggestionService.getSuggestion(suggestionRequest);
		
		return ResponseEntity.ok(response);
	}
}