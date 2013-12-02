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

import org.springframework.beans.factory.annotation.Autowired;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
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

	
	//@Autowired
	private StudentMgntDao studentMgntDao = new StudentMgntDao();
	
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
		List<String> students = teacher.getStudents();

		String studentId = null;

		for (String temp : students) {
			if (temp == String.valueOf(id)) {
				studentId = temp;
				break;
			}
		}

		if (null == studentId) {
			throw new HTTPClientException(
					"Cannot find Student id under this teacher!");

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

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		
		List<Student> students = studentMgntDao.findAllStudents();
		//List<Student> students = teacher.getStudents();

		StudentsDto response = new StudentsDto();
		response.setStudents(students);

		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-student")
	public Response createStudent(@PathParam("teacherId") long teacherId,
			@Valid Student student) throws Exception {

		if (!TeacherRepository.getTeacherRepository().containsKey(teacherId)) {
			throw new HTTPClientException("Teacher id does not match!");
		}

		long id = SequenceGenerator.nextStudentId();
		student.setUserId(String.valueOf(id));

		Teacher teacher = TeacherRepository.getTeacherRepository().get(
				teacherId);
		teacher.getStudents().add(student.getUserId());
		
		TeacherRepository.getTeacherRepository().put(String.valueOf(teacherId), teacher);
		
		studentMgntDao.addStudent(student);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-student", "/teachers/" + teacher.getUserId()
				+ "/students/" + student.getUserId(), "GET"));

		return Response.status(201).entity(links).build();

	}

}
