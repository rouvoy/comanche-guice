package comanche.servlets;

import java.io.IOException;

import comanche.Request;

public class HelloWorldServlet extends ServletRequestHandler {

	@Override
	protected String get(Request r) throws IOException {
		return "<html><head/><body><h1>Hello, World!</h1></body></html>";
	}
}
