package comanche;

import java.io.IOException;

/** Signals that an HTTP request can not be completed */
public class RequestFailureException extends IOException {
	private static final long serialVersionUID = 1L;

	public RequestFailureException(String msg) {
		super(msg);
	}
}
