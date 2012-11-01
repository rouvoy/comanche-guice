package comanche.loggers;

import java.io.PrintStream;

import javax.inject.Inject;

import comanche.api.Logger;

/** Logs to System.err */
public class BasicLogger implements Logger {
	@Inject
	private PrintStream out;
	
	/* (non-Javadoc)
	 * @see comanche.loggers.Logger#log(java.lang.String)
	 */
	public void log(String msg) {
		out.println(msg);
	}
}
