/**
 * 
 */
package comanche;

import static com.google.inject.name.Names.named;

import java.io.IOException;
import java.net.ServerSocket;

import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import comanche.api.RequestHandler;
import comanche.handlers.ErrorRequestHandler;
import comanche.handlers.FileRequestHandler;
import comanche.handlers.RequestAnalyzer;

/**
 * @author rouvoy
 * 
 */
public class WebModule extends AbstractModule {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(RequestHandler.class).annotatedWith(named("analyzer")).to(
				RequestAnalyzer.class);
		bind(RequestHandler.class).annotatedWith(named("file")).to(
				FileRequestHandler.class);
		bind(RequestHandler.class).annotatedWith(named("error")).to(
				ErrorRequestHandler.class);
	}

	@Provides
	ServerSocket serverSocket(@Named("port") int port) throws IOException {
		return new ServerSocket(port);
	}
}
