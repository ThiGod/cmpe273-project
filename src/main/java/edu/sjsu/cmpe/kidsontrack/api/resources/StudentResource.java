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

import com.xerox.amazonws.sns.NotificationService;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.CoursesDto;
import edu.sjsu.cmpe.kidsontrack.dto.GradesDto;
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
	
	private NotificationService sns;
	private String topicArn;

	public StudentResource() {
		// do nothing
	}
	
	public StudentResource(NotificationService sns, String topicArn) {
		this.sns = sns;
		this.topicArn = topicArn;
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-student")
	public StudentDto getStudentById(@PathParam("teacherId") long teacherId,
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
	@Path("/{id}/courses")
	@Timed(name = "view-student-courses")
	public CoursesDto getAllCoursesForStudentById(@PathParam("teacherId") long teacherId,
			@PathParam("id") long id) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		List<Course> courses = studentMgntDao.getAllCourses(String
				.valueOf(id));

		CoursesDto response = new CoursesDto();
		response.setCourses(courses);

		return response;
	}
	
	@GET
	@Path("/{id}/scores")
	@Timed(name = "view-all-scores")
	public GradesDto getAllScores(@PathParam("teacherId") long teacherId, @PathParam("id") long id) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		List<Grade> grades = studentMgntDao.getAllGrades(String
				.valueOf(id));

		GradesDto response = new GradesDto();
		response.setGrades(grades);
		
		return response;
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
		
		String email = student.getEmail();
		
		System.out.println("Register email: " + email + " to kidsontrack topic in AWS SNS.");
		
		sns.subscribe(topicArn, "email", email);

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
