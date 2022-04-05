package com.joel.service;

import java.util.List;

import com.joel.domain.City;

public interface FileService {

	List<City> parseFile(String file);
}