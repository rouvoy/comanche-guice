package comanche.handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import javax.inject.Inject;

import comanche.Request;
import comanche.api.Logger;
import comanche.api.RequestHandler;

/**
 * Parses the requested URL to extract parameters, then delegates the call to
 * another request handler (e.g. {@link FileRequestHandler})
 * 
 */
public class RequestAnalyzer implements RequestHandler {
	/** implicit contract: must not be null */
	// private FileRequestHandler rh = new FileRequestHandler();
	private final List<RequestHandler> rh;

	/**
	 * implicit contract: may be null protected by if (l != null) below
	 */
	// private BasicLogger l = new BasicLogger();
	private final Logger l;

	@Inject
	public RequestAnalyzer(List<RequestHandler> rh, Logger l) {
		this.rh = rh;
		this.l = l;
	}

	// The rest of the code does not change
	public void handleRequest(Request r) throws IOException {
		r.in = r.s.getInputStream();
		String rq = new LineNumberReader(new InputStreamReader(r.in))
				.readLine();
		if (l != null)
			l.log(rq);
		if (rq.startsWith("GET ")) {
			r.url = rq.substring(5, rq.indexOf(' ', 4));
			IOException e = null;
			for (RequestHandler handler : rh)
				try {
					handler.handleRequest(r);
					return;
				} catch (IOException ex) {
					e = ex;
				}
			throw e;
		}
	}
}
