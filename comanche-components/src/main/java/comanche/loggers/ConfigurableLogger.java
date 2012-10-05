package comanche.loggers;

import javax.inject.Inject;
import javax.inject.Named;

/** Logs to System.err with a prefix given at instantiation time */
public class ConfigurableLogger extends BasicLogger {
	private final String header;

	@Inject
	public ConfigurableLogger(@Named("header") String header) {
		this.header = header;
	}

	/* (non-Javadoc)
	 * @see comanche.loggers.Logger#log(java.lang.String)
	 */
	public void log(String msg) {
		super.log(header + msg);
	}
}
