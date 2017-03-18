package org.webserver;

import java.io.File;

public interface Validator {

	public static final String FILETXT = ".txt";

	default boolean validateFile(String file) {

		File f = new File(file);
		boolean exist = f.exists() ? true : false;
		boolean isTXT = file.endsWith(FILETXT);
		boolean isDirectory = f.isDirectory();
		
		return (exist && (isTXT || isDirectory));
	}
}
