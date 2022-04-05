package com.joel.service;

import java.util.List;

import com.joel.model.SuggestionRequestModel;
import com.joel.model.SuggestionResponseModel;

public interface SuggestionService {

	List<SuggestionResponseModel> getSuggestion(SuggestionRequestModel suggestionRequest);
}
