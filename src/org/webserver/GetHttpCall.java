package org.webserver;

import java.io.File;

public class GetHttpCall implements HttpCall {

	private static Validator validator;

	static {

	}

	@Override
	public void execute(HttpRequest req, HttpResponse resp) {

		String filePath = FILESYSTEM + req.getUri();
		File file = new File(filePath);
		
		validator = new Validator() {

			@Override
			public boolean validateFile(String file) {
				boolean truth = file.endsWith(Validator.FILE) ? true : false;
				return truth;
			}

		};

		if (!file.exists() && !validator.validateFile(filePath) ) {
			resp.sendError();
			return;
		}

		resp.sendFile(filePath);

	}

}
