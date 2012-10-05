package comanche;

import java.io.IOException;

public class HelloWorldServlet extends ServletRequestHandler {

	@Override
	protected String get(Request r) throws IOException {
		return "<html><head/><body><h1>Hello, World!</h1></body></html>";
	}
}
