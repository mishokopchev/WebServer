package org.webserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

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
			System.err.println("Remote object" + remote);
			Servlet main = new Servlet();
			boolean validRequest = Filter.validate(request, response);
			if (!validRequest) {
				response.sendError(HTTPCode.SERVERERROR);
				return;
			}
			main.execute(request, response);
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
