package edu.sjsu.cmpe.kidsontrack.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.ui.views.StudentView;
import edu.sjsu.cmpe.kidsontrack.ui.views.StudentsView;

@Path("/students")
public class StudentsResource {
	private StudentMgntDaoInterface studentMgntDao;
	
	public StudentsResource(StudentMgntDaoInterface studentMgntDao) {
		this.studentMgntDao = studentMgntDao;
	}
	
	@GET
	public StudentsView illustrate() {
		return new StudentsView(studentMgntDao.findAllStudents());
	}
	
	@GET
	@Path("/{id}")
	public StudentView teacher(@PathParam("id") String id) {
		return new StudentView(studentMgntDao.findStudentById(id));
	}
}
