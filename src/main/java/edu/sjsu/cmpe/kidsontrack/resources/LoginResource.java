package edu.sjsu.cmpe.kidsontrack.resources;

import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
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
	
	public void verifyLogin(@FormParam("username") String Username, 
			@FormParam("password") String Password) throws URISyntaxException {
		
	}
}
