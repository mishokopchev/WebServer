package org.webserver;

import java.io.IOException;
import java.net.Socket;

public class ServerWorkerThread extends Thread {
	private Socket remote;

	public ServerWorkerThread(Socket socket) {
		this.remote = socket;

	}

	@Override
	public void run() {
		try {

			HttpRequest request = new HttpRequest(remote.getInputStream());
			HttpResponse response = new HttpResponse(remote.getOutputStream());

			boolean validRequest = Filter.validate(request, response);
			if (!validRequest) {
				response.sendError(HTTPCode.SERVERERROR);
				return;
			}
			HttpCall call = HTPPRequestFactory.getInstance(request);
			call.execute(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				remote.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
