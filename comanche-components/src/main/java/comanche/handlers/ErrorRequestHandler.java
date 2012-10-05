package comanche.handlers;

import java.io.IOException;

import comanche.Request;
import comanche.api.RequestHandler;

/** Sends an HTTP 404 in response to the request */
public class ErrorRequestHandler implements RequestHandler {
	/* (non-Javadoc)
	 * @see comanche.handlers.RequestHandler#handleRequest(comanche.Request)
	 */
	public void handleRequest(Request r) throws IOException {
		r.out.write("HTTP/1.0 404 Not Found\n\n".getBytes());
		r.out.write("Comanche: document not found.".getBytes());
	}
}
