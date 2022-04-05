package com.joel.service;

import com.joel.domain.CityQuery;
import com.joel.model.CityResponseModel;

public interface SuggestionService {

	Iterable<CityResponseModel> getSuggestion(CityQuery query);
}