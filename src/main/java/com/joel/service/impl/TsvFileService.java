package com.joel.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joel.domain.City;
import com.joel.domain.Country;
import com.joel.service.FileService;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que se encarga de procesar
 * los datos que se encuentran en un
 * archivo tipo .tsv a un conjunto de filas.
 * 
 * @author Joel Rubio
 *
 */
@Slf4j
@Service
public class TsvFileService implements FileService {

	private static final String TAB_CHARACTER = "\t";
	private static final int INDEX_NAME       = 1;
	private static final int INDEX_LATITUDE   = 4;
	private static final int INDEX_LONGITUDE  = 5;
	private static final int INDEX_COUNTRY    = 8;
	private static final int INDEX_CODE       = 10;
	private List<City> cities                 = new ArrayList<>();
	
	
	/**
	 * Lee un archivo y retorna sus líneas o filas 
	 * en forma de lista con sus columnas en un arreglo
	 * de tipo String.
	 * 
	 * 
	 * @param file
	 * @return
	 */
	@Override
	public List<City> parseFile(String file) {
		
		if (!cities.isEmpty())
			return cloneList(cities);
		
		log.info("Parsing TSV file...");
		
		Path filePath = Paths.get(file);
		
		try {
			
			cities = Files.lines(filePath)
						.parallel()
						.map(this::mapToCity)
						.collect(Collectors.toList());
			
		} catch (IOException exception) {
			
			exception.printStackTrace();
			
			log.error("TSV File couldn't be parsed");
			
			return List.of();
		}
		
		log.info("TSV File parsed");
		
		return cloneList(cities);
	}
	
	private List<City> cloneList(List<City> cities) {
		
		return cities.parallelStream()
			.map(city -> city.clone())
			.collect(Collectors.toList());
	}
	
	private City mapToCity(String fileRow) {
		
		String[] splittedRow = fileRow.split(TAB_CHARACTER);
		
		return new City(splittedRow[INDEX_NAME], 
						splittedRow[INDEX_LATITUDE], 
						splittedRow[INDEX_LONGITUDE], 
						Country.getCountry(splittedRow[INDEX_COUNTRY]),
						splittedRow[INDEX_CODE],
						0.0);
	}
}