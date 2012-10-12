package comanche.loggers;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

/** Logs to System.err with a time-stamped message */
public class DatedLogger extends BasicLogger {
	@Inject
	Provider<Date> date;
	
	/* (non-Javadoc)
	 * @see comanche.loggers.Logger#log(java.lang.String)
	 */
	public void log(String msg) {
		super.log(date.get() + ": " + msg);
	}
}
