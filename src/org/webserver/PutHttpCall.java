package org.webserver;

import java.io.File;
import java.io.IOException;

public class PutHttpCall implements HttpCall {

	private static Validator validator;

	static {
		validator = new Validator(){
			
		};
	}

	@Override
	public void execute(HttpRequest req, HttpResponse resp) {


		String uri = req.getUri();

		String filePath = FILESYSTEM + uri;
		File file = new File(filePath);

		if(!validator.validateFile(filePath)){
			resp.sendError(HTTPCode.ERROR);
			return;
		}
		
		try {

			boolean createdFile = file.createNewFile();

			if (!createdFile) {
				file.delete();
				file.createNewFile();
			}
			resp.sendOK();

		} catch (IOException e) {
			e.printStackTrace();
			resp.sendError(HTTPCode.ERROR);
		}

	}

}
