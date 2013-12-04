package edu.sjsu.cmpe.kidsontrack.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.sjsu.cmpe.kidsontrack.ui.views.TeachersView;

@Path("/teachers")
public class TeachersResource {
	public TeachersResource() {
		
	}
	
	@GET
	public TeachersView illustrate() {
		return new TeachersView();
	}
}
