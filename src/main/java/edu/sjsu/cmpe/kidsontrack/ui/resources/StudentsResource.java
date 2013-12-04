package edu.sjsu.cmpe.kidsontrack.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.sjsu.cmpe.kidsontrack.ui.views.StudentsView;

@Path("/students")
public class StudentsResource {
	public StudentsResource() {
		
	}
	
	@GET
	public StudentsView illustrate() {
		return new StudentsView();
	}
}
