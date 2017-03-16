package org.webserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

	private BufferedReader buffer;
	private String method;
	private String uri;
	private String protocol;

	private Map<String, String[]> headers;
	private Map<String, String[]> parameters;

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
				generateLine(line);

				this.headers = new HashMap<String, String[]>();
				this.parameters = new HashMap<String, String[]>();
				String lines = null;

				while ((lines = buffer.readLine()) != null) {
					if (lines.isEmpty()) {
						break;
					}
					this.parseHeaders(lines);
				}

				
			} catch (Exception e) {

			}

		}

	}

	public void generateLine(String line) {
		if (line != null || line.isEmpty()) {

			String[] params = line.split(" ");

			this.setMethod(params[0].trim().toLowerCase());
			this.setProtocol(params[2].trim().toLowerCase());
			this.setUri(params[1].trim().toLowerCase());

		} else {
			//
		}
	}

	public void parseHeaders(String line) {
		if (line != null && !line.isEmpty()) {
			String name = line.substring(0, line.indexOf(":")).trim();
			String[] params = line.substring(name.length() + 2).split(",");
			headers.put(name, params);
		}

	}

}
