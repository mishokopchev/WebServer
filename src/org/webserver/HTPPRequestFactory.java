package org.webserver;

public class HTPPRequestFactory {

	public static HttpCall getInstance(HttpRequest request) {
		HttpCall instance = null;
		
		
		String method = request.getMethod().toLowerCase();
		switch (method) {
		case "get":
			instance = new GetHttpCall();
			break;
		case "put":
			instance = new PutHttpCall(request.getBody());
			break;
		case "delete":
			instance = new DeleteHttpCall();

		}
		return instance;

	}

}
