/**
 * 
 */
package comanche;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.name.Names.named;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import comanche.api.Logger;
import comanche.api.RequestHandler;
import comanche.api.Scheduler;
import comanche.loggers.ConfigurableLogger;
import comanche.schedulers.SequentialScheduler;

/**
 * @author rouvoy
 * 
 */
public class WebServerModule extends AbstractModule {
	private static final int PORT = 8080;

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Scheduler.class).to(SequentialScheduler.class);

		bindConstant().annotatedWith(named("port")).to(PORT);
		bindConstant().annotatedWith(named("header")).to(
				"[Comanche:" + PORT + "] ");
		bind(Logger.class).to(ConfigurableLogger.class);

		bind(PrintStream.class).toInstance(System.out);
	}

	@Provides
	List<RequestHandler> handlers(@Named("file") RequestHandler h1,
			@Named("error") RequestHandler h2) {
		List<RequestHandler> handlers = new LinkedList<RequestHandler>();
		handlers.add(h1);
		handlers.add(h2);
		return handlers;
	}

	public static final void main(String[] argv) {
		Injector injector = createInjector(new WebModule(),
				new WebServerModule());
		injector.getInstance(RequestReceiver.class).run();
	}
}
