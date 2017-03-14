package org.webserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class HttpRequest {

	private BufferedReader buffer;

	private StringBuilder raw;

	private String method;
	private String uri;
	private String protocol;

	public StringBuilder getRaw() {
		return raw;
	}

	public void setRaw(StringBuilder raw) {
		this.raw = raw;
	}

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
				System.out.println(line);

				String[] params = line.split(" ");
				System.out.println(Arrays.toString(params));

				this.setMethod(params[0].trim().toLowerCase());
				this.setProtocol(params[2].trim().toLowerCase());
				this.setUri(params[1].trim().toLowerCase());

			} catch (Exception e) {

			}

		}

	}

}
