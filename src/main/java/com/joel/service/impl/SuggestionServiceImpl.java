package com.joel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joel.model.SuggestionRequestModel;
import com.joel.model.SuggestionResponseModel;
import com.joel.service.FileService;
import com.joel.service.SuggestionService;

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

	private static final int INDEX_NAME      = 1;
	private static final int INDEX_LATITUDE  = 4;
	private static final int INDEX_LONGITUDE = 5;

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
	public List<SuggestionResponseModel> getSuggestion(SuggestionRequestModel suggestionRequest) {
		
		//read file and get input
		List<String[]> rows = this.fileService.parseFile(filePath);
		
		if (rows.isEmpty())
			return List.of();
		
		log.info("Iterating file content...");
		
		//iterate the content and find matches
		List<String[]> result = rows.parallelStream()
			.filter(row -> row[INDEX_NAME].contains(suggestionRequest.getQ()))
			.collect(Collectors.toList());
		
		log.info("Finish iterating file content");
		
		if (result.isEmpty())
			return List.of();
		
		
		log.info("Assembling content");
		
		//assembly the content
		List<SuggestionResponseModel> response = result.stream()
				.map(row -> SuggestionResponseModel.builder()
						.name(row[INDEX_NAME])
						.latitude(row[INDEX_LATITUDE])
						.longitude(row[INDEX_LONGITUDE])
						.score(0)
						.build())
				.collect(Collectors.toList());
		
		response.forEach(System.out::println);
		
		log.info("Finish assembling content");
		
		return response;
	}
}