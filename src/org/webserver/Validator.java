package org.webserver;

public interface Validator {

	public static final String FILE = ".txt";

	default boolean validateFile(String file) {
		if (file.endsWith(FILE)) {
			return true;
		}
		return false;
	}
}
