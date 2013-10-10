package edu.sjsu.cmpe.kidsontrack.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.kidsontrack.views.ExampleView;

@Path("/kidsontrack/example")
@Produces(MediaType.TEXT_HTML)
public class ExampleResource {
	
	public ExampleResource() {
		
	}
	
    @GET
    public ExampleView illustrate() {
        return new ExampleView("Mot\u00f6rhead");
    }

}
