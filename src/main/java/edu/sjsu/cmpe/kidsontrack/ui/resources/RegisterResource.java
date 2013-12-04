package edu.sjsu.cmpe.kidsontrack.ui.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.LinkDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinksDto;
import edu.sjsu.cmpe.kidsontrack.ui.views.RegisterView;
import edu.sjsu.cmpe.kidsontrack.util.SequenceGenerator;

@Path("/register")
@Produces(MediaType.TEXT_HTML)
public class RegisterResource {
	
	private Student student = new Student();
	private Teacher teacher = new Teacher();
	private TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();
	
	public RegisterResource() {
		
	}
	
	@GET
	public RegisterView illustrate() {
		return new RegisterView();
	}
	
	@POST
	public Response addNewUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, 
			@FormParam("Email") String email, @FormParam("Password") String password,
			@FormParam("role") int role) throws URISyntaxException {
			teacher.setEmail(email);
			teacher.setFirstName(firstName);
			teacher.setLastName(lastName);
			teacher.setPassword(password);
			teacher.setRole("Teacher");
			
			long id = SequenceGenerator.nextTeacherId();
			teacher.setUserId(String.valueOf(id));

			teacherMgntDao.addTeacher(teacher);
			URI uriTeachers = new URI("http://localhost:8080/kidsontrack/teachers/" + id);
			
			return Response.seeOther(uriTeachers).build();
	}
}
