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

		try {
			String filePath = FILESYSTEM + req.getUri();
			File file = new File(filePath);

			if (!file.exists() || !validator.validateFile(filePath)) {
				resp.sendError(HTTPCode.ERROR);
				return;
			}

			resp.sendFile(filePath);
		} catch (Exception e) {
			resp.sendError(HTTPCode.ERROR);
		}
	}

}
