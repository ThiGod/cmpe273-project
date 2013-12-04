package edu.sjsu.cmpe.kidsontrack.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.kidsontrack.ui.views.WelcomeView;

@Path("/welcome")
@Produces(MediaType.TEXT_HTML)
public class WelcomeResource {
	
	public WelcomeResource() {
		
	}
	
	@GET
    public WelcomeView illustrate() {
        return new WelcomeView("Welcome to KidsOnTrack!");
    }
}
