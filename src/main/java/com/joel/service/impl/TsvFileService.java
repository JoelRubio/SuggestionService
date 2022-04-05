package com.joel.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joel.service.FileService;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que se encarga de procesar
 * los datos que se encuentran en un
 * archivo tipo .tsv a un conjunto de filas.
 * 
 * @author joel
 *
 */
@Slf4j
@Service
public class TsvFileService implements FileService {

	private static final String TAB_SEPARATOR = "\t";
	private List<String[]> rows;
	
	public TsvFileService() {
		
		rows = new ArrayList<>();
	}
	
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
	public List<String[]> parseFile(String file) {
		
		if (!rows.isEmpty())
			return rows;
		
		log.info("Parsing TSV file...");
		
		Path filePath = Paths.get(file);
		
		try {
			
			rows = Files.lines(filePath)
					.parallel()
					.map(line -> line.split(TAB_SEPARATOR))
					.collect(Collectors.toList());
			
		} catch (IOException exception) {
			
			exception.printStackTrace();
			
			log.error("TSV File couldn't be parsed");
			
			return List.of();
		}
		
		log.info("TSV File parsed");
		
		return rows;
	}
}