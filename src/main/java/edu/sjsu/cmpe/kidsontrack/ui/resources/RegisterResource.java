package edu.sjsu.cmpe.kidsontrack.ui.resources;

import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.ui.views.RegisterView;

@Path("/register")
@Produces(MediaType.TEXT_HTML)
public class RegisterResource {
	
	private Student student = new Student();
	private Teacher teacher = new Teacher();
	
	public RegisterResource() {
		
	}
	
	@GET
	public RegisterView illustrate() {
		return new RegisterView();
	}
	
	/*
	@POST
	public Response addNewUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, 
			@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("role") int role) throws URISyntaxException {

	}
	*/
}
