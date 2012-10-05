package comanche.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import comanche.RequestFailureException;
import comanche.Request;
import comanche.api.RequestHandler;

/**
 * Sends a the content of a file of the file system if the requested URL matches
 * one.
 */
public class FileRequestHandler implements RequestHandler {
	private static final String HTTP_RESPONSE_200 = "HTTP/1.0 200 OK\n\n";

	/* (non-Javadoc)
	 * @see comanche.handlers.RequestHandler#handleRequest(comanche.Request)
	 */
	public void handleRequest(Request r) throws IOException {
		File f = new File(r.url);
		if (f.exists() && !f.isDirectory()) {
			InputStream is = new FileInputStream(f);
			byte[] data = new byte[is.available()];
			is.read(data);
			is.close();
			r.out.write(HTTP_RESPONSE_200.getBytes());
			r.out.write(data);
		} else {
			throw new RequestFailureException("No such file found");
		}
	}
}
