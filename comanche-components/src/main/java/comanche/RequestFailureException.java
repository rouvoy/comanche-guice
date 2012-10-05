package comanche;

/** Signals that an HTTP request can not be completed */
public class RequestFailureException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequestFailureException(String msg) {
		super(msg);
	}
}
