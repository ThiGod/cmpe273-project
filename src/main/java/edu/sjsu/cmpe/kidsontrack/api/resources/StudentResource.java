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

import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.dto.LinkDto;
import edu.sjsu.cmpe.kidsontrack.dto.LinksDto;
import edu.sjsu.cmpe.kidsontrack.dto.StudentDto;
import edu.sjsu.cmpe.kidsontrack.dto.StudentsDto;
import edu.sjsu.cmpe.kidsontrack.exception.HTTPClientException;
import edu.sjsu.cmpe.kidsontrack.repository.TeacherRepository;
import edu.sjsu.cmpe.kidsontrack.util.SequenceGenerator;

@Path("/v1/teachers/{teacherId}/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

	public StudentResource() {
		// do nothing
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-student")
	public StudentDto getStudent(@PathParam("teacherId") long teacherId,
			@PathParam("id") long id) throws Exception {

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		List<Student> students = teacher.getStudents();

		Student student = null;

		for (Student temp : students) {
			if (temp.getUserId() == id) {
				student = temp;
				break;
			}
		}

		if (null == student) {
			throw new HTTPClientException(
					"Cannot find Student id under this teacher!");

		}

		StudentDto reviewResponse = new StudentDto(student);
		reviewResponse.setStudent(student);
		reviewResponse.addLink(new LinkDto("view-student", "/teachers/"
				+ teacher.getUserId() + "/students/" + student.getUserId(),
				"GET"));

		return reviewResponse;
	}

	@GET
	@Timed(name = "view-students")
	public StudentsDto getAllReview(@PathParam("teacherId") long teacherId)
			throws Exception {

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		List<Student> students = teacher.getStudents();

		StudentsDto response = new StudentsDto();
		response.setStudents(students);

		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-student")
	public Response createReview(@PathParam("teacherId") long teacherId,
			@Valid Student student) throws Exception {

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		long id = SequenceGenerator.nextStudentId();
		student.setUserId(id);

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		teacher.getStudents().add(student);
		
		TeacherRepository.getTeacherRepository().put(teacherId, teacher);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-student", "/teachers/" + teacher.getUserId()
				+ "/students/" + student.getUserId(), "GET"));

		return Response.status(201).entity(links).build();

	}

}
