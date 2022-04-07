package com.joel.service;


/**
 * @author Joel Rubio
 *
 * @param <T>
 * @param <U>
 */
public interface ScoreService<T, U> {

	void apply(T t, U u);
}