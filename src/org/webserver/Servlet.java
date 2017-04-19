package org.webserver;

public class Servlet {

	public void execute(HttpRequest request, HttpResponse response) {
		HttpCall call = HTPPRequestFactory.getInstance(request);
		call.execute(request, response);
		
	}

}
