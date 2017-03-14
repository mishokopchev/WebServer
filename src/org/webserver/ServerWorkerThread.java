package org.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerWorkerThread extends Thread {
	private Socket remote;

	public ServerWorkerThread(Socket socket) {
		this.remote = socket;

	}

	@Override
	public void run() {

		try {

			// wait for a connection

			System.err.println("Remote object" + remote);

			// remote is now the connected socket
			System.out.println("Connection, sending data.");
			BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
			PrintWriter out = new PrintWriter(remote.getOutputStream());

			HttpRequest request = new HttpRequest(remote.getInputStream());
			HttpResponse response = new HttpResponse(remote.getOutputStream());

			boolean validRequest = Filter.validate(request, response);
			if (!validRequest) {
				response.sendError();
				remote.close();
				return;
			}

			HttpCall call = HTPPRequestFactory.getInstance(request);

			call.execute(request, response);

			//
			// out.println("HTTP/1.0 404 ");
			// out.println("Content-Type: text/html");
			// out.println("Server: Bot");
			// // this blank line signals the end of the headers
			// out.println("");
			// // Send the HTML page
			// out.println("<H1>Welcome to the Ultra Mini-WebServer</H2>");
			// out.flush();

			in.close();
			out.close();
			remote.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
