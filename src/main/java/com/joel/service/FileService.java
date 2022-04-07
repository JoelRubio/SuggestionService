package com.joel.service;

import java.util.List;

import org.springframework.core.io.Resource;


/**
 * @author Joel Rubio
 *
 * @param <T>
 */
public interface FileService<T> {

	List<T> parseFile(Resource file);
}