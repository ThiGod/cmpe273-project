package edu.sjsu.cmpe.kidsontrack.api.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.LinkDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinksDto;
import edu.sjsu.cmpe.kidsontrack.dto.StudentDto;
import edu.sjsu.cmpe.kidsontrack.dto.StudentsDto;
import edu.sjsu.cmpe.kidsontrack.exception.HTTPClientException;
import edu.sjsu.cmpe.kidsontrack.util.SequenceGenerator;

@Path("/v1/teachers/{teacherId}/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

	private StudentMgntDaoInterface studentMgntDao = new StudentMgntDao();

	private TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();

	public StudentResource() {
		// do nothing
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-student")
	public StudentDto getStudent(@PathParam("teacherId") long teacherId,
			@PathParam("id") long id) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Student student = studentMgntDao.findStudentById(String.valueOf(id));

		StudentDto reviewResponse = new StudentDto(student);
		reviewResponse.setStudent(student);
		reviewResponse.addLink(new LinkDto("view-student", "/teachers/"
				+ teacher.getUserId() + "/students/" + student.getUserId(),
				"GET"));

		return reviewResponse;
	}

	@GET
	@Timed(name = "view-students")
	public StudentsDto getAllStudents(@PathParam("teacherId") long teacherId)
			throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		List<Student> students = studentMgntDao.findAllStudents();

		StudentsDto response = new StudentsDto();
		response.setStudents(students);

		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-student")
	public Response createStudent(@PathParam("teacherId") long teacherId,
			@Valid Student student) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		long id = SequenceGenerator.nextStudentId();
		student.setUserId(String.valueOf(id));

		studentMgntDao.addStudent(student);

		System.out.println("Add student: " + student + " to Student DB");

		teacherMgntDao
				.addStudent(String.valueOf(teacherId), String.valueOf(id));

		System.out.println("Add student: " + student + " to Teacher DB");

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-student", "/teachers/"
				+ teacher.getUserId() + "/students/" + student.getUserId(),
				"GET"));

		return Response.status(201).entity(links).build();

	}

	@DELETE
	@Path("/{id}")
	@Timed(name = "delete-student")
	public Response deleteStudentById(@PathParam("teacherId") long teacherId,
			@PathParam("id") long id) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		studentMgntDao.deleteStudentById(String.valueOf(id));
		
		teacherMgntDao.removeStudent(String.valueOf(teacherId), String.valueOf(id));

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-student", "/teachers/" + teacherId
				+ "/students/", "POST"));

		return Response.ok(links).build();

	}

	@DELETE
	@Path("/")
	@Timed(name = "delete-all-students")
	public Response deleteAllStudents(@PathParam("teacherId") long teacherId)
			throws Exception {

		studentMgntDao.deleteStudentTable();
		
		
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-student", "/teachers/" + teacherId
				+ "/students/", "POST"));

		return Response.ok(links).build();

	}

}
