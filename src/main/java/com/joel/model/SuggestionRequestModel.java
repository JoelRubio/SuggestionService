package com.joel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuggestionRequestModel {

	private String q; //represent a complete or partial search
	private String latitude;
	private String longitude;
}
