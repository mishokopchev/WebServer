package org.webserver;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		
//		if(args.length > 0)  {
//            System.err.println("Required parameters: <port>");
//            return;
//        }
//		Integer port = args[0]!=null ? Integer.parseInt(args[0]): 80;
		
		WebServer webserver = new WebServer(2222);
		webserver.start();
		
	}
}
