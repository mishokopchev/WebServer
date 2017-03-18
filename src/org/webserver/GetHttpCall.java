package org.webserver;

import java.io.File;

public class GetHttpCall implements HttpCall {

	private static Validator validator;

	static {
		validator = new Validator() {
		};
	}

	@Override
	public void execute(HttpRequest req, HttpResponse resp) {
		// this server works only with txt files and folders
		try {
			String filePath = FILESYSTEM + req.getUri();

			if (!validator.validateFile(filePath)) {
				resp.sendError(HTTPCode.ERROR);
				return;
			}

			File file = new File(filePath);
			
			if(file.isDirectory()){
				resp.sendDirectory(filePath);
			}else{				
				resp.sendFile(filePath);
			}
			
		} catch (Exception e) {
			resp.sendError(HTTPCode.ERROR);
		}
	}

}
