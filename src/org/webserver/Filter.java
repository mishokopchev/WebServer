package org.webserver;

public class Filter {

	private static String METHOD_REGEX = "(put|get|delete)";

	public static synchronized boolean validate(HttpRequest req, HttpResponse resp) {

		boolean validation;

		validation = req.getMethod().matches(METHOD_REGEX) ? true : false;

		return validation;

	}
}
