package org.webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HttpResponse {

	private BufferedWriter buffer;
	private PrintWriter out;
	private BufferedReader reader;

	public HttpResponse(OutputStream outputStream) {
		this.out = new PrintWriter(outputStream);
		this.buffer = new BufferedWriter(new OutputStreamWriter(outputStream));
	}

	public BufferedWriter getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedWriter buffer) {
		this.buffer = buffer;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}


	public void sendError() {

		out.println("HTTP/1.0 404 ");
		out.println("Content-Type: text/html");
		out.println("Server: Bot");
		// this blank line signals the end of the headers
		out.println("");
		// Send the HTML page
		out.println("<H1>Error</H1>");
		out.flush();

	}

	public void sendFile(String filename) {

		out.println("HTTP/1.0 404 ");
		out.println("Content-Type: text/html");
		out.println("Server: Bot");
		// this blank line signals the end of the headers
		out.println("");
		// Send the HTML page

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Construct BufferedReader from InputStreamReader
		reader = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		
		try {
			while ((line = reader.readLine()) != null)
				out.println("<H5>" + line + " exist </H5>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.flush();

	}

	public void sendOK() {

		out.println("HTTP/1.0 200 ");
		out.println("Content-Type: text/html");
		out.println("Server: Bot");
		// this blank line signals the end of the headers
		out.println("");
		// Send the HTML page
		out.println("<H1>" + "OK,operation was succesfull" + " exist </H1>");
		out.flush();
	}
}