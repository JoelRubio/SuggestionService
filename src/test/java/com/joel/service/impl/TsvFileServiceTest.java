package com.joel.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TsvFileServiceTest {

	@Mock
	private TsvFileService fileService;
	
	@Test
	public void getLinesOfTsvFile() {
		
		//given
		String filePath                 = "somePath.tsv";
		List<String[]> expectedResponse = List.of(new String[] {"line1"}, new String[] {"line2"});
		List<String[]> actualResponse;
		
		//when
		when(fileService.parseFile(filePath)).thenReturn(expectedResponse);
		
		//execute
		actualResponse = fileService.parseFile(filePath);
		
		//then
		assertThat(actualResponse).isEqualTo(expectedResponse);
	}
}