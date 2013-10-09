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

import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.CourseDto;
import edu.sjsu.cmpe.kidsontrack.dto.CoursesDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinkDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinksDto;
import edu.sjsu.cmpe.kidsontrack.exception.HTTPClientException;
import edu.sjsu.cmpe.kidsontrack.repository.TeacherRepository;
import edu.sjsu.cmpe.kidsontrack.util.SequenceGenerator;

@Path("/v1/teachers/{teacherId}/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {

	public CourseResource() {
		// do nothing
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-course")
	public CourseDto getCourse(@PathParam("teacherId") long teacherId,
			@PathParam("id") long id) throws Exception {

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		List<Course> courses = teacher.getCourses();

		Course course = null;

		for (Course temp : courses) {
			if (temp.getCourseId() == id) {
				course = temp;
				break;
			}
		}

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
	@Timed(name = "view-courses")
	public CoursesDto getAllCourse(@PathParam("teacherId") long teacherId)
			throws Exception {

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		
		List<Course> courses = teacher.getCourses();

		CoursesDto response = new CoursesDto();
		response.setCourses(courses);

		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-course")
	public Response createReview(@PathParam("teacherId") long teacherId,
			@Valid Course course) throws Exception {

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		long id = SequenceGenerator.nextCourseId();
		course.setCourseId(id);

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		
		teacher.getCourses().add(course);
		
		TeacherRepository.getTeacherRepository().put(teacherId, teacher);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-course", "/teachers/" + teacher.getUserId()
				+ "/courses/" + course.getCourseId(), "GET"));

		return Response.status(201).entity(links).build();

	}

}
