package edu.sjsu.cmpe.kidsontrack.api.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.CourseDto;
import edu.sjsu.cmpe.kidsontrack.dto.CoursesDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinkDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinksDto;
import edu.sjsu.cmpe.kidsontrack.dto.StudentDto;
import edu.sjsu.cmpe.kidsontrack.exception.HTTPClientException;
import edu.sjsu.cmpe.kidsontrack.util.SequenceGenerator;

@Path("/v1/teachers/{teacherId}/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

	private TeacherMgntDaoInterface teacherMgntDao = new TeacherMgntDao();

	public CourseResource() {
		// do nothing
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-course")
	public CourseDto getCourseById(@PathParam("teacherId") long teacherId,
			@PathParam("id") long id) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Course course = teacherMgntDao.getCourseById(String.valueOf(teacherId),
				String.valueOf(id));

		if (null == course) {
			throw new HTTPClientException(
					"Cannot find Course id under this teacher!");

		}

		CourseDto response = new CourseDto(course);
		response.setCourse(course);
		response.addLink(new LinkDto("view-course", "/teachers/"
				+ teacher.getUserId() + "/courses/" + course.getCourseId(),
				"GET"));

		return response;
	}

	@GET
	@Path("/{id}/highestscore")
	@Timed(name = "view-highest-score-student-per-course")
	public StudentDto getHighestScoreStudentByCourseById(
			@PathParam("teacherId") long teacherId, @PathParam("id") long id)
			throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Student student = teacherMgntDao.getHiStudentByCourse(
				String.valueOf(teacherId), String.valueOf(id));

		StudentDto response = new StudentDto(student);
		response.setStudent(student);

		return response;

	}

	@GET
	@Timed(name = "view-courses")
	public CoursesDto getAllCourses(@PathParam("teacherId") long teacherId)
			throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		List<Course> courses = teacherMgntDao.getAllCourses(String
				.valueOf(teacherId));

		CoursesDto response = new CoursesDto();
		response.setCourses(courses);

		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-course")
	public Response createCourse(@PathParam("teacherId") long teacherId,
			@Valid Course course) throws Exception {

		Teacher teacher = teacherMgntDao.findTeacherById(String
				.valueOf(teacherId));

		if (null == teacher) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		long id = SequenceGenerator.nextCourseId();
		course.setCourseId(String.valueOf(id));

		teacherMgntDao.addCourse(String.valueOf(teacherId), course);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-course", "/teachers/"
				+ teacher.getUserId() + "/courses/" + course.getCourseId(),
				"GET"));

		return Response.status(201).entity(links).build();

	}

}
