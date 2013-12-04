package edu.sjsu.cmpe.kidsontrack.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import edu.sjsu.cmpe.kidsontrack.dao.TeacherMgntDaoInterface;
import edu.sjsu.cmpe.kidsontrack.ui.views.TeacherView;
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
	public TeacherView teacher(@PathParam("id") String id) {
		return new TeacherView(teacherMgntDao.findTeacherById(id));
	}
}
