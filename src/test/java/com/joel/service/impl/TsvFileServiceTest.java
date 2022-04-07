package com.joel.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.joel.domain.City;
import com.joel.domain.Country;
import com.joel.service.CloudStorage;


@ExtendWith(MockitoExtension.class)
public class TsvFileServiceTest {

	@InjectMocks
	private TsvFileService fileService;
	
	@Mock
	private CloudStorage cloudStorage;
	
	private File testFile;
	
	@BeforeEach
	public void init() throws IOException {
		
		testFile = File.createTempFile("someFile", ".tsv");
	}
	
	@AfterEach
	public void tearDown() {
		
		testFile.deleteOnExit();
	}
	
	@Test
	public void getLinesOfTsvFile() {
		
		//given
		Path path = testFile.toPath();
		List<City> actualResponse;
		List<City> expectedResponse = List.of(new City("name1", "latitude1", "longitude1", Country.USA, "MD", 0), 
											  new City("name2", "latitude2", "longitude2", Country.Canada, "CA", 0));
		
		//when
		when(cloudStorage.getPath()).thenReturn(Optional.of(path));
		when(fileService.parseFile()).thenReturn(expectedResponse);
		
		//execute
		actualResponse = fileService.parseFile();
		
		//then
		assertThat(actualResponse).isEqualTo(expectedResponse);
	}
}