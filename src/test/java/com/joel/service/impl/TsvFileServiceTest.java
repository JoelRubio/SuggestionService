package com.joel.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joel.domain.City;
import com.joel.domain.Country;


@ExtendWith(MockitoExtension.class)
public class TsvFileServiceTest {

	@Mock
	private TsvFileService fileService;
	
	@Test
	public void getLinesOfTsvFile() {
		
		//given
		String filePath = "somePath.tsv";
		List<City> actualResponse;
		List<City> expectedResponse = List.of(new City("name1", "latitude1", "longitude1", Country.USA, "MD", 0), 
											  new City("name2", "latitude2", "longitude2", Country.Canada, "CA", 0));
		
		//when
		when(fileService.parseFile(filePath)).thenReturn(expectedResponse);
		
		//execute
		actualResponse = fileService.parseFile(filePath);
		
		//then
		assertThat(actualResponse).isEqualTo(expectedResponse);
	}
}