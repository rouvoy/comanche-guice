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

	private final int port;

	@Inject
	private PrintStream out;
	
	@Inject
	public RequestReceiver(Scheduler s, @Named("analyzer") RequestHandler rh,
			@Named("port") int port) {
		this.s = s;
		this.rh = rh;
		this.port = port;
	}

	// functional aspect
	public void run() {
		new Thread(new Runnable() {

			public void run() {
				try {
					ServerSocket ss = new ServerSocket(port);
					out.println("HTTP Server ready on port " + port);
					while (true) {
						final Socket socket = ss.accept();
						s.schedule(new Runnable() {
							public void run() {
								try {
									Request r = new Request(socket);
									rh.handleRequest(r);
									socket.close();
								} catch (Exception _) {
									try {
										socket.close();
									} catch (IOException e) {
										throw new RuntimeException(
												"this should never happen");
									}
									/**
									 * Nothing else, the server must keep on
									 * working
									 */
								}
							}
						});
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();
	}
}
