package com.joel.service;

import java.util.List;

import com.joel.domain.City;

/**
 * @author Joel Rubio
 *
 */
public interface FileService {

	List<City> parseFile(String file);
}