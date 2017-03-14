package org.webserver;

public class Filter {

	private static String METHOD_REGEX = "(put|get|delete)";

	public static synchronized boolean validate(HttpRequest req, HttpResponse resp) {

		boolean method = false, uri = false, protocol = false;

		method = req.getMethod().matches(METHOD_REGEX) ? true : false;

		if (req.getUri() != null && req.getUri().length() > 1) {
			uri = true;
		}
		if (req.getProtocol() != null && req.getProtocol().contains("http")) {
			protocol = true;
		}

		return (method && uri && protocol);

	}
}
