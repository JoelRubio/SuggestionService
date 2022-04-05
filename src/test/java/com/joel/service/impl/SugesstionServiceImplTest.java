package com.joel.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joel.domain.CityQuery;
import com.joel.domain.Latitude;
import com.joel.domain.Longitude;
import com.joel.domain.Name;
import com.joel.model.CityResponseModel;
import com.joel.service.FileService;


@ExtendWith(MockitoExtension.class)
public class SugesstionServiceImplTest {

	@InjectMocks
	private SuggestionServiceImpl suggestionService;
	
	@Mock
	private FileService fileService;
	
	
	@Test
	public void getSuggestionOfCities() {
		
		//given
//		List<City> cities                      = List.of();
//		String filePath                        = "somePath.tsv";
		CityQuery query = new CityQuery(new Name("London, ON, Canada"), new Latitude("42.98339"), new Longitude("-81.23304"));
		List<CityResponseModel> expected = List.of();
		Iterable<CityResponseModel> actualResponse;
		
		//when
		//when(fileService.parseFile(filePath, request)).thenReturn(rows);
		when(suggestionService.getSuggestion(query)).thenReturn(expected);
		
		//execute
		actualResponse = suggestionService.getSuggestion(query);
		
		//then
		//verify(fileService, times(1)).parseFile(filePath, request);
		
		assertThat(actualResponse).isEqualTo(expected);
	}
}