package comanche.api;

import java.io.IOException;

import comanche.Request;

public interface RequestHandler {

	void handleRequest(Request r) throws IOException;

}