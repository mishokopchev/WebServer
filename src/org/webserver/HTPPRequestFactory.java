package org.webserver;

public class HTPPRequestFactory {

	public static HttpCall getInstance(HttpRequest request) {
		HttpCall instance = null;

		if (request.getMethod().equals(Method.GET.toString().toLowerCase())) {
			instance = new GetHttpCall();

		}
		if (request.getMethod().equals(Method.PUT.toString().toLowerCase())) {
			instance = new PutHttpCall();
		}
		if (request.getMethod().equals(Method.DELETE.toString().toLowerCase())) {
			instance = new DeleteHttpCall();

		}

		return instance;

	}

}
