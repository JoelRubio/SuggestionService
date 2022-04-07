package com.joel.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joel.domain.City;
import com.joel.domain.CityQuery;
import com.joel.model.CityResponseModel;
import com.joel.model.Latitude;
import com.joel.model.Longitude;
import com.joel.model.Name;
import com.joel.service.FileService;
import com.joel.service.FilterService;
import com.joel.service.ScoreService;


@ExtendWith(MockitoExtension.class)
public class SugesstionServiceImplTest {

	@InjectMocks
	@Spy
	private SuggestionServiceImpl suggestionService;
	
	@Mock
	private FileService<City> fileService;
	
	@Mock
	private FilterService<City, CityQuery> filterService;
	
	@Mock
	private ScoreService<City, CityQuery> scoreService;
	
	
	@Test
	public void getSuggestionOfCities() {
		
		//given
		CityQuery query  = new CityQuery(new Name("Londo"), new Latitude("42.98339"), new Longitude("-81.23304"));
		String name      = "London, NY, USA";
		String latitude  = "42.98339";
		String longitude = "-81.23304";
		double score     = 0.0;
		Iterable<CityResponseModel> expected = List.of(CityResponseModel.builder()
				.name(name)
				.latitude(latitude)
				.longitude(longitude)
				.score(score)
				.build());
		Iterable<CityResponseModel> actualResponse;
		
		//when
		doReturn(expected).when(suggestionService).buildResponse(List.of(), query);
		when(suggestionService.getSuggestion(query)).thenReturn(expected);
		
		//execute
		actualResponse = suggestionService.getSuggestion(query);
		
		//then
		assertThat(actualResponse).isEqualTo(expected);
	}
}