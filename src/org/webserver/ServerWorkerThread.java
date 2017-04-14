package org.webserver;

import java.io.BufferedReader;
import java.io.IOException;
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

//			HttpRequest request = new HttpRequest(remote.getInputStream());
//			HttpResponse response = new HttpResponse(remote.getOutputStream());
//			System.err.println("Remote object" + remote);

			System.out.println("Connection, sending data.");
			 BufferedReader in = new BufferedReader(new
			 InputStreamReader(remote.getInputStream()));
			 PrintWriter out = new PrintWriter(remote.getOutputStream());


			 String str = ".";
			 while (!str.equals("")) {
			 str = in.readLine();
			 System.out.println(str);
			 }
//
//			boolean validRequest = Filter.validate(request, response);
//			if (!validRequest) {
//				response.sendError(HTTPCode.SERVERERROR);
//				return;
//			}
//			HttpCall call = HTPPRequestFactory.getInstance(request);
//			call.execute(request, response);

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
