package comanche.loggers;

import comanche.api.Logger;

/** Logs a message together with a stack trace to get the execution path */
public class StackTraceLogger implements Logger {
	/* (non-Javadoc)
	 * @see comanche.loggers.Logger#log(java.lang.String)
	 */
	public void log(final String msg) {
		new Exception(msg).printStackTrace();
	}
}
