package edu.sjsu.cmpe.kidsontrack.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class HTTPClientException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HTTPClientException(String message) {
		 super(Response.status(Response.Status.BAD_REQUEST)
	             .entity(message).type(MediaType.APPLICATION_JSON).build());
	}

}
