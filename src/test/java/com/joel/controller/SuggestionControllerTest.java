package com.joel.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.joel.domain.CityQuery;
import com.joel.domain.Latitude;
import com.joel.domain.Longitude;
import com.joel.domain.Name;
import com.joel.model.CityResponseModel;
import com.joel.service.SuggestionService;


@WebMvcTest(controllers = SuggestionController.class)
public class SuggestionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SuggestionService suggestionService;
	
	private String uri;
	
	@BeforeEach
	public void init() {
		
		uri = "/suggestions";
	}
	
	@Test
	public void getSuggestionOfCities() throws Exception {
		
		//given
		String nameReq      = "London";
		String latitudeReq  = "43.70011";
		String longitudeReq = "-79.4163";
		CityQuery query = new CityQuery(new Name(nameReq), new Latitude(latitudeReq), new Longitude(longitudeReq));
		
		String nameRes      = "London, ON, Canada";
		String latitudeRes  = "42.98339";
		String longitudeRes = "-81.23304";
		double scoreRes     = 0.1;
		CityResponseModel city1 = CityResponseModel.builder()
				.name(nameRes)
				.latitude(latitudeRes)
				.longitude(longitudeRes)
				.score(scoreRes)
				.build();
		
		List<CityResponseModel> citiesResponse = List.of(city1);
		String request = String.format("%s?q=%s&latitude=%s&longitude=%s", uri, nameReq, latitudeReq, longitudeReq);
		
		//when
		when(suggestionService.getSuggestion(query)).thenReturn(citiesResponse);
		
		//then
		this.mockMvc
			.perform(get(request)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name", is(nameRes)))
			.andExpect(jsonPath("$[0].latitude", is(latitudeRes)))
			.andExpect(jsonPath("$[0].longitude", is(longitudeRes)))
			.andExpect(jsonPath("$[0].score", is(scoreRes)));
	}
}