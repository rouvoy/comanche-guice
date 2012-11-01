package comanche;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.inject.Inject;
import javax.inject.Named;

import comanche.api.RequestHandler;
import comanche.api.Scheduler;

/** Listens to a socket and enters an infinite loop to handle incoming requests. */
public class RequestReceiver implements Runnable {
	// private SequentialScheduler s = new SequentialScheduler();
	private final Scheduler s;
	// private RequestAnalyzer rh = new RequestAnalyzer();
	private final RequestHandler rh;

	private final ServerSocket ss;

	@Inject
	private PrintStream out;

	@Inject
	public RequestReceiver(Scheduler s, @Named("analyzer") RequestHandler rh,
			ServerSocket socket) {
		this.s = s;
		this.rh = rh;
		this.ss = socket;
	}

	public void run() {
		try {
			out.println("HTTP Server ready on port " + ss.getLocalPort());
			while (true) {
				final Socket socket = ss.accept();
				s.schedule(new Runnable() {
					public void run() {
						try {
							rh.handleRequest(new Request(socket));
							socket.close();
						} catch (Exception _) {
							try {
								socket.close();
							} catch (IOException e) {
								throw new RuntimeException(
										"This should never happen");
							}
						}
					}
				});
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
