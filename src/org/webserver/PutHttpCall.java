package org.webserver;

import java.io.File;
import java.io.IOException;

public class PutHttpCall implements HttpCall {

	@Override
	public void execute(HttpRequest req, HttpResponse resp) {

		String uri = req.getUri();

		String filePath = FILESYSTEM + uri;

		File file = new File(filePath);

		try {

			boolean createdFile = file.createNewFile();
			System.out.println(createdFile);
			
			if (!createdFile) {
				file.delete();
				file.createNewFile();
			}
			resp.sendOK();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
