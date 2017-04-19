package org.webserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PutHttpCall implements HttpCall {

	private List<String> params = new ArrayList<String>();
	private String body;

	private static Validator validator;

	public PutHttpCall(String body) {
		this.body = body;
	}

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

		try {
			boolean putted = this.put();
			if (putted) {
				this.writeContent(FILESYSTEM + req.getUri());
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
					if (builder.endsWith(".txt")) {
						file.createNewFile();
					} else {
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

	private void writeContent(String file) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(this.body);

		} catch (IOException e) {
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
	}

}
