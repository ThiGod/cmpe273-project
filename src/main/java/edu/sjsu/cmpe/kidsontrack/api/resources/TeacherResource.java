package edu.sjsu.cmpe.kidsontrack.api.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.LinkDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinksDto;
import edu.sjsu.cmpe.kidsontrack.dto.TeacherResponseDto;
import edu.sjsu.cmpe.kidsontrack.dto.TeachersDto;
import edu.sjsu.cmpe.kidsontrack.util.SequenceGenerator;

@Path("/v1/teachers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeacherResource {

	private TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();

	public TeacherResource() {
		// do nothing
	}

	@GET
	@Path("/")
	@Timed(name = "view-all-teachers")
	public TeachersDto getAllTeachers() throws Exception {

		List<Teacher> teachers = teacherMgntDao.findAllTeachers();

		TeachersDto response = new TeachersDto();
		response.setTeachers(teachers);

		response.addLink(new LinkDto("create-teacher", "/teachers", "POST"));

		return response;
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-teacher")
	public TeacherResponseDto getTeacherById(@PathParam("id") long id)
			throws Exception {

		
		Teacher teacher =teacherMgntDao.findTeacherById(String.valueOf(id));

		TeacherResponseDto teacherResponse = new TeacherResponseDto(teacher);

		teacherResponse.addLink(new LinkDto("view-teacher", "/teachers/"
				+ teacher.getUserId(), "GET"));
		teacherResponse.addLink(new LinkDto("update-teacher", "/teachers/"
				+ teacher.getUserId(), "PUT"));
		teacherResponse.addLink(new LinkDto("delete-teacher", "/teachers/"
				+ teacher.getUserId(), "DELETE"));
		teacherResponse.addLink(new LinkDto("create-teacher", "/teachers/"
				+ teacher.getUserId(), "POST"));

		List<String> studentList = teacher.getStudents();

		// if students > 0, add view-all-students link
		if (studentList.size() > 0) {
			teacherResponse.addLink(new LinkDto("view-all-studentss",
					"/teachers/" + teacher.getUserId() + "/students", "GET"));
		}

		List<Course> courseList = teacher.getCourses();

		// if courses > 0, add view-all-courses link
		if (courseList.size() > 0) {
			teacherResponse.addLink(new LinkDto("view-all-courses",
					"/teachers/" + teacher.getUserId() + "/courses", "GET"));
		}

		return teacherResponse;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-teacher")
	public Response createTeacher(@Valid Teacher teacher) throws Exception {

		long id = SequenceGenerator.nextTeacherId();
		teacher.setUserId(String.valueOf(id));

		teacherMgntDao.addTeacher(teacher);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-teacher", "/teachers/"
				+ teacher.getUserId(), "GET"));
		links.addLink(new LinkDto("update-teacher", "/teachers/"
				+ teacher.getUserId(), "PUT"));
		links.addLink(new LinkDto("delete-teacher", "/teachers/"
				+ teacher.getUserId(), "DELETE"));
		links.addLink(new LinkDto("create-teacher", "/teachers/"
				+ teacher.getUserId(), "POST"));

		return Response.status(201).entity(links).build();

	}

	@PUT
	@Timed(name = "update-teacher")
	public Response updateTeacher(@Valid Teacher teacher) throws Exception {

		teacherMgntDao.addTeacher(teacher);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-teacher", "/teachers/"
				+ teacher.getUserId(), "GET"));
		links.addLink(new LinkDto("update-teacher", "/teachers/"
				+ teacher.getUserId(), "PUT"));
		links.addLink(new LinkDto("delete-teacher", "/teachers/"
				+ teacher.getUserId(), "DELETE"));
		links.addLink(new LinkDto("create-teacher", "/teachers/"
				+ teacher.getUserId(), "POST"));

		List<String> studentList = teacher.getStudents();

		// if students > 0, add view-all-students link
		if (studentList.size() > 0) {
			links.addLink(new LinkDto("view-all-studentss", "/teachers/"
					+ teacher.getUserId() + "/students", "GET"));
		}

		List<Course> courseList = teacher.getCourses();

		// if courses > 0, add view-all-courses link
		if (courseList.size() > 0) {
			links.addLink(new LinkDto("view-all-courses", "/teachers/"
					+ teacher.getUserId() + "/courses", "GET"));
		}

		return Response.ok(links).build();

	}

	@DELETE
	@Path("/{id}")
	@Timed(name = "delete-teacher")
	public Response deleteTeacherById(@PathParam("id") long id)
			throws Exception {

		teacherMgntDao.deleteTeacherById(String.valueOf(id));

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-teacher", "/teachers", "POST"));

		return Response.ok(links).build();

	}
	
	@DELETE
	@Path("/")
	@Timed(name = "delete-all-teachers")
	public Response deleteAllTeachers()
			throws Exception {

		teacherMgntDao.deleteTeacherTable();

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-teacher", "/teachers", "POST"));

		return Response.ok(links).build();

	}
}
