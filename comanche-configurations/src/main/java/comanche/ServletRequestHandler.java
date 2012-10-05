/**
 * 
 */
package comanche;

import java.io.IOException;

import comanche.api.RequestHandler;

/**
 * @author rouvoy
 *
 */
public abstract class ServletRequestHandler implements RequestHandler {
	private static final String HTTP_RESPONSE_200 = "HTTP/1.0 200 OK\n\n";

	/* (non-Javadoc)
	 * @see comanche.api.RequestHandler#handleRequest(comanche.Request)
	 */
	public void handleRequest(Request r) throws IOException {
		String data = get(r);
		r.out.write(HTTP_RESPONSE_200.getBytes());
		r.out.write(data.getBytes());
	}
	
	protected abstract String get(Request r) throws IOException;
}
