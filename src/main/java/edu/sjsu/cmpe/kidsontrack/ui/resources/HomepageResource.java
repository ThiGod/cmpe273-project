package edu.sjsu.cmpe.kidsontrack.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.kidsontrack.ui.views.HomepageView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomepageResource {
	public HomepageResource() {
		
	}
	
	@GET
	public HomepageView illustrate() {
		return new HomepageView();
	}
}
