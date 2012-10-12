/**
 * 
 */
package comanche;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.name.Names.named;

import java.io.PrintStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Provider;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import comanche.api.Logger;
import comanche.api.RequestHandler;
import comanche.api.Scheduler;
import comanche.loggers.DatedLogger;
import comanche.schedulers.MultiThreadScheduler;
import comanche.servlets.HelloWorldServlet;

/**
 * @author rouvoy
 * 
 */
public class WebContainerModule extends AbstractModule {
	private static final int PORT = 8888;

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Scheduler.class).to(MultiThreadScheduler.class);

		bind(Date.class).toProvider(DateProvider.class);
		bind(Logger.class).to(DatedLogger.class);
		bind(PrintStream.class).toInstance(System.err);

		bind(RequestHandler.class).annotatedWith(named("servlet")).to(
				HelloWorldServlet.class);

		bindConstant().annotatedWith(named("port")).to(PORT);
	}

	@Provides
	List<RequestHandler> handlers(@Named("servlet") RequestHandler h1,
			@Named("error") RequestHandler h2) {
		List<RequestHandler> handlers = new LinkedList<RequestHandler>();
		handlers.add(h1);
		handlers.add(h2);
		return handlers;
	}

	private static class DateProvider implements Provider<Date> {
		@Override
		public Date get() {
			return new Date();
		}
	}

	public static final void main(String[] argv) {
		Injector injector = createInjector(new WebModule(),
				new WebContainerModule());
		injector.getInstance(RequestReceiver.class).run();
	}
}
