package edu.sjsu.cmpe.kidsontrack.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.sjsu.cmpe.kidsontrack.views.LoginView;

@Path("/login")
public class LoginResource {	
	public LoginResource() {
		
	}
	
	@GET
	public LoginView illustrate() {
		return new LoginView();
	}
	
}
