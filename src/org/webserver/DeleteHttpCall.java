package org.webserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteHttpCall implements HttpCall {

	@Override
	public void execute(HttpRequest req, HttpResponse resp) {

		String uri = req.getUri();
		String filePath = FILESYSTEM + uri;
		File file = new File(filePath);

		if (file.exists()) {
			file.delete();
			resp.sendOK();
		} else {
			resp.sendError(HTTPCode.SERVERERROR);
		}

	}

}
