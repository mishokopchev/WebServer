package org.webserver;

public class Servlet{

	public Servlet() {

	}
	public void process() {

	}

	public void execute(HttpRequest request, HttpResponse response) {
		HttpCall call = HTPPRequestFactory.getInstance(request);
		call.execute(request, response);
		
	}

}
