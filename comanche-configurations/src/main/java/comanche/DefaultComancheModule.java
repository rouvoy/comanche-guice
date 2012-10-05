/**
 * 
 */
package comanche;

import static com.google.inject.name.Names.named;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import comanche.api.Logger;
import comanche.api.RequestHandler;
import comanche.api.Scheduler;
import comanche.handlers.ErrorRequestHandler;
import comanche.handlers.FileRequestHandler;
import comanche.handlers.RequestAnalyzer;
import comanche.loggers.BasicLogger;
import comanche.schedulers.SequentialScheduler;

/**
 * @author rouvoy
 * 
 */
public class DefaultComancheModule extends AbstractModule {
	private static final int PORT = 8080;

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Scheduler.class).to(SequentialScheduler.class);
		
		bind(Logger.class).to(BasicLogger.class).asEagerSingleton();

		bind(PrintStream.class).toInstance(System.out);

		bind(RequestHandler.class).annotatedWith(named("analyzer")).to(
				RequestAnalyzer.class);
		
		bind(Integer.class).annotatedWith(named("port")).toInstance(PORT);
	}

	@Provides
	List<RequestHandler> requestHandlers() {
		ArrayList<RequestHandler> list = new ArrayList<RequestHandler>();
		list.add(new FileRequestHandler());
		list.add(new ErrorRequestHandler());
		return list;
	}
	
	public static final void main(String[] argv) {
		Injector injector = Guice.createInjector(new DefaultComancheModule());
		injector.getInstance(RequestReceiver.class).run();
	}
}
