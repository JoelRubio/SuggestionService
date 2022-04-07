package com.joel.service;

import java.nio.file.Path;
import java.util.Optional;


/**
 * @author Joel Rubio
 *
 */
public interface CloudStorage {

	Optional<Path> getPath();
}