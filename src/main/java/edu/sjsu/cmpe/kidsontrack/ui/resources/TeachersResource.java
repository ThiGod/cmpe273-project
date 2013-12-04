package edu.sjsu.cmpe.kidsontrack.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.StudentMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDao;
import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.ui.views.TeachersView;

@Path("/teachers")
public class TeachersResource {
	private TeacherMgntDaoInterface teacherMgntDao;
	
	public TeachersResource(TeacherMgntDaoInterface teacherMgntDao) {
		this.teacherMgntDao = teacherMgntDao;
	}
	
	@GET
	public TeachersView illustrate() {
		return new TeachersView(teacherMgntDao.findAllTeachers());
	}
	
	@GET
	@Path("/{id}")
	public TeachersView teacher() {
		return new TeachersView(teacherMgntDao.findAllTeachers());
	}
}
