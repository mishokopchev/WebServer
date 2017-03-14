package org.webserver;

public interface HttpCall {

	public static String FILESYSTEM = "/Users/mihailkopchev/Documents/workspace/Webserver";

	void execute(HttpRequest req, HttpResponse resp);

}
