package org.webserver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PutHttpCall implements HttpCall {

	private List<String> params = new ArrayList<String>();
	private static Validator validator;

	static {
		validator = new Validator() {

			public boolean validateFile(String file) {
				File f = new File(file);
				boolean isTXT = file.endsWith(FILETXT);
				boolean isDirectory = true;
				return isTXT || isDirectory;
			}

		};
	}

	@Override
	public void execute(HttpRequest req, HttpResponse resp) {
		String filePath = FILESYSTEM + req.getUri();
		if (!validator.validateFile(filePath)) {
			resp.sendError(HTTPCode.ERROR);
			return;
		}

		this.params = req.getParameters();
		File file = new File(filePath);

		try {
			boolean putted = this.put();
			if (putted) {
				resp.sendOK();
				return;

			} else {
				resp.sendError(HTTPCode.ERROR);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HTTPCode.ERROR);
		}

	}

	private boolean isDirectory(File file) {
		return file.isDirectory();
	}

	private boolean fileExist(File file) {
		return file.exists();
	}

	private boolean put() {
		String builder = (FILESYSTEM);

		for (String entity : this.params) {

			String resource = File.separator + entity;
			builder += (resource);
			File file = new File(builder);

			if (!this.fileExist(file)) {
				try {
					if(builder.endsWith(".txt")){
						file.createNewFile();
					}
					else{
						file.mkdir();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

}
