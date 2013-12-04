package edu.sjsu.cmpe.kidsontrack.ui.resources;

import java.net.*;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.ui.views.LoginView;

@Path("/login")
@Produces(MediaType.TEXT_HTML)
public class LoginResource {	
	
	private StudentMgntDaoInterface studentMgntDao = new StudentMgntDao();
	private TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();
	
	public LoginResource() {
		
	}
	
	@GET
	public LoginView illustrate() {
		return new LoginView();
	}
	
	@POST
	public Response verifyLogin(@FormParam("Email") String email,
			@FormParam("Password") String password) throws URISyntaxException {
		URI uriLogin = new URI("http://localhost:9000/kidsontrack/register");
		
		if(!studentMgntDao.isFound(email, password)&&teacherMgntDao.isFound(email, password)) {
			System.out.println("Hello teacher");
			String uid = teacherMgntDao.authenticate(email, password);
			URI uriTeachers = new URI("http://localhost:9000/kidsontrack/teachers/" + uid);
			return Response.seeOther(uriTeachers).build();
		}
		if(!teacherMgntDao.isFound(email, password)&&studentMgntDao.isFound(email, password)) {
			System.out.println("Hello Student");
			String uid = studentMgntDao.authenticate(email, password);
			URI uriStudents = new URI("http://localhost:9000/kidsontrack/students/" + uid);
			return Response.seeOther(uriStudents).build();
		}
		else {
			System.out.println("User not found!");
			return Response.seeOther(uriLogin).build();
		}
	}
}
