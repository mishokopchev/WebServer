package org.webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpResponse {

	private PrintWriter out;
	private BufferedReader reader;

	public HttpResponse(OutputStream outputStream) {
		this.out = new PrintWriter(outputStream);
	}

	public void sendHeaders(HTTPCode code) {

		out.println("HTTP/1.0");
		out.println(code);
		out.println("Content-Type: text/html");
		out.println("Server: Bot");
		// this blank line signals the end of the headers
		out.println("");
	}

	public void sendError(HTTPCode code) {
		sendHeaders(code);
		// Send the HTML page
		out.println("<H1>Error</H1>");
		this.flush();
	}

	public void sendFile(String filename) {

		sendHeaders(HTTPCode.OK);
		// Send the HTML page

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		reader = new BufferedReader(new InputStreamReader(fis));

		String line = null;

		try {
			while ((line = reader.readLine()) != null)
				out.println("<H5>" + line + "  </H5>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.flush();

	}

	public void sendOK() {

		sendHeaders(HTTPCode.OK);
		// Send the HTML page
		out.println("<H1>" + "OK,operation was succesfull" + " </H1>");
		this.flush();
	}

	void flush() {
		this.out.flush();
	}

	public void sendDirectory(String filePath) {
		File directory = new File(filePath);

		if (directory != null) {
			sendHeaders(HTTPCode.OK);
			out.println("<H1>" + "You requested a folder" + " </H1>");
			out.println("<H1>" + "Contet:" + " </H1>");

			String[] entries = directory.list();
			for (String currentFile : entries) {
				out.println("<H4>*" + currentFile + " </H4>");
			}

			this.flush();
		} else
			sendError(HTTPCode.ERROR);

	}

}