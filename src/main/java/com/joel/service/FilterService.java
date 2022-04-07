package com.joel.service;

import java.util.List;


/**
 * @author Joel Rubio
 *
 * @param <T>
 * @param <U>
 */
public interface FilterService<T, U> {

	List<T> filter(List<T> list, U u);
	boolean verify(T t, U u);
}