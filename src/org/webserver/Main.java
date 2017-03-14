package org.webserver;

public class Main {
	
	public static void main(String[] args) {
	
		WebServer webserver = new WebServer(2222);
		webserver.start();
		
	}
}
