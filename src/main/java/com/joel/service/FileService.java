package com.joel.service;

import java.util.List;


/**
 * @author Joel Rubio
 *
 * @param <T>
 */
public interface FileService<T> {

	List<T> parseFile();
}