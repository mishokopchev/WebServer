package org.webserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class HttpRequest {

	private BufferedReader buffer;
	private String method;
	private String uri;
	private String protocol;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public HttpRequest(InputStream reader) {

		buffer = new BufferedReader(new InputStreamReader(reader));

		synchronized (buffer) {

			try {

				String line = buffer.readLine();
				String[] params = line.split(" ");

				this.setMethod(params[0].trim().toLowerCase());
				this.setProtocol(params[2].trim().toLowerCase());
				this.setUri(params[1].trim().toLowerCase());

			} catch (Exception e) {

			}

		}

	}

}
