package org.webserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeleteHttpCall implements HttpCall {

	private static Validator validator;
	private List<String> parameters = new ArrayList<String>();
	static {
		validator = new Validator() {

			public boolean validateFile(String f) {
				File file = new File(f);
				boolean exist = file.exists();
				return exist;
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
		File currentFile = new File(filePath);
		if (currentFile.isFile()) {
			this.deleteFile(filePath);
		} else {
			this.deleteDirectory(filePath);
		}
		resp.sendOK();
	}

	private void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file != null) {
			file.delete();
		}
	}

	private void deleteDirectory(String filePath) {
		File file = new File(filePath);
		if (file != null) {
			String[] files = file.list();
			for (String currentFile : files) {
				if (currentFile.endsWith(".DS_Store")) {
					File f = new File(filePath + File.separator + currentFile);
					f.delete();
					continue;
				}
			File f = new File(filePath + File.separator + currentFile);
			if (f.isFile()) {
				f.delete();
			} else {
				deleteDirectory(filePath + File.separator + currentFile);
			}
		}
		file.delete();
	}

	}

	private void delete() {

	}

}
