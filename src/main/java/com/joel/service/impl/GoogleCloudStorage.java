package com.joel.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.contrib.nio.CloudStorageConfiguration;
import com.google.cloud.storage.contrib.nio.CloudStorageFileSystem;
import com.joel.service.CloudStorage;

import lombok.extern.slf4j.Slf4j;


/**
 * Clase que obtiene un archivo
 * desde la nube de Google.
 * 
 * @author Joel Rubio
 *
 */
@Slf4j
@Service
public class GoogleCloudStorage implements CloudStorage {

	@Value("${gcloud.projectId}")
	private String projectId;
	
	@Value("${gcloud.jsonKey}")
	private String jsonKeyPath;
	
	@Value("${gcloud.bucket}")
	private String bucketName;
	
	@Value("${gcloud.object}")
	private String objectName;
	
	
	/**
	 * Obtiene un archivo desde la nube de Google,
	 * estableciendo las credenciales necesarias
	 * para autenticarse.
	 * 
	 * @return ruta del archivo 
	 */
	@Override
	public Optional<Path> getPath() {
		
		CloudStorageFileSystem fileSystem = null;
		
		log.info("Getting file system from Google Cloud Storage...");
		
		try {
			
			fileSystem = CloudStorageFileSystem.forBucket(
					bucketName,
					CloudStorageConfiguration.DEFAULT,
					StorageOptions.newBuilder()
						.setProjectId(projectId)
						.setCredentials(GoogleCredentials.fromStream(new FileInputStream(jsonKeyPath)))
						.build());
			
		} catch (IOException exception) {
			
			log.error("Error while getting file system from Google Cloud Storage");
			
			exception.printStackTrace();
			
			return Optional.empty();
		}
		
		log.info("File system obtained from Google Cloud Storage");
		
		return Optional.of(fileSystem.getPath(objectName));
	}
}