package comanche.loggers;

import java.util.Date;

/** Logs to System.err with a time-stamped message */
public class DatedLogger extends BasicLogger {
	/* (non-Javadoc)
	 * @see comanche.loggers.Logger#log(java.lang.String)
	 */
	public void log(String msg) {
		super.log((new Date()) + ": " + msg);
	}
}
