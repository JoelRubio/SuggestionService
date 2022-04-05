package com.joel.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joel.model.SuggestionRequestModel;
import com.joel.model.SuggestionResponseModel;
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
//		List<String[]> rows                    = List.of();
//		String filePath                        = "somePath.tsv";
		SuggestionRequestModel request         = new SuggestionRequestModel("London, ON, Canada", "42.98339", "-81.23304");  
		List<SuggestionResponseModel> expected = List.of();
		List<SuggestionResponseModel> actualResponse;
		
		//when
		//when(fileService.parseFile(filePath)).thenReturn(rows);
		when(suggestionService.getSuggestion(request)).thenReturn(expected);
		
		//execute
		actualResponse = suggestionService.getSuggestion(request);
		
		//then
		//verify(fileService, times(1)).parseFile(filePath);
		
		assertThat(actualResponse).isEqualTo(expected);
	}
}