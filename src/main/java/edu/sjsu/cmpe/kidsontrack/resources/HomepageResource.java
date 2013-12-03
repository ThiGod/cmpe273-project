package edu.sjsu.cmpe.kidsontrack.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.sjsu.cmpe.kidsontrack.views.HomepageView;

@Path("/")
public class HomepageResource {
	public HomepageResource() {
		
	}
	
	@GET
	public HomepageView illustrate() {
		return new HomepageView();
	}
}
