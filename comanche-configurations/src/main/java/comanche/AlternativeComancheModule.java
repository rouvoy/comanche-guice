/**
 * 
 */
package comanche;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.name.Names.named;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import comanche.api.Logger;
import comanche.api.RequestHandler;
import comanche.api.Scheduler;
import comanche.handlers.ErrorRequestHandler;
import comanche.handlers.RequestAnalyzer;
import comanche.loggers.ConfigurableLogger;
import comanche.schedulers.MultiThreadScheduler;

/**
 * @author rouvoy
 * 
 */
public class AlternativeComancheModule extends AbstractModule {
	private static final int PORT = 8888;

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Scheduler.class).to(MultiThreadScheduler.class);

		bind(String.class).annotatedWith(named("header")).toInstance(
				"[Comanche:" + PORT + "] ");
		bind(Logger.class).to(ConfigurableLogger.class);
		bind(PrintStream.class).toInstance(System.err);

		bind(RequestHandler.class).annotatedWith(named("analyzer")).to(
				RequestAnalyzer.class);

		bind(Integer.class).annotatedWith(named("port")).toInstance(PORT);
	}

	@Provides
	List<RequestHandler> requestHandlers() {
		ArrayList<RequestHandler> list = new ArrayList<RequestHandler>();
		list.add(new HelloWorldServlet());
		list.add(new ErrorRequestHandler());
		return list;
	}

	public static final void main(String[] argv) {
		Injector injector = createInjector(new AlternativeComancheModule());
		injector.getInstance(RequestReceiver.class).run();
	}
}
