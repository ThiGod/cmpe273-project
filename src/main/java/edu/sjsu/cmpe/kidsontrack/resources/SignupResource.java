package edu.sjsu.cmpe.kidsontrack.resources;

import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.sjsu.cmpe.kidsontrack.views.SignupView;

@Path("/signup")
public class SignupResource {
	public SignupResource() {
		
	}
	
	@GET
	public SignupView illustrate() {
		return new SignupView();
	}
	
	public void verifySignup(@FormParam("Email") String Username, 
			@FormParam("Password") String Password,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName) throws URISyntaxException {
		
	}
}
