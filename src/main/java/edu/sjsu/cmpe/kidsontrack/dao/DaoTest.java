package edu.sjsu.cmpe.kidsontrack.dao;

import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Scores;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.domain.User;

public class DaoTest {

	public static Teacher generateTeacher(String id, int num)
	{
		Teacher t = new Teacher();
		
		t.setUserId(id + num);
		t.setFirstName("name" + num);
		t.setLastName("last"+ num);
		t.setEmail("email"+num);
		
		return t;
		
	}
	
	public static Student generateStudent(String id, int num)
	{
		Student t = new Student();
		
		t.setUserId(id + num);
		t.setFirstName("name" + num);
		t.setLastName("last"+ num);
		t.setEmail("email"+num);
		
		return t;
		
	}
	
	public static Grade generateGrade(String type, String courseId)
	{
		Scores sc = generateScore(type, 10);
		Grade g = new Grade();
		
		g.setCourseId(courseId);
		g.addScore(sc);
		
		return g;
		
	}
	
	public static Scores generateScore(String type, double point)
	{
		Scores sc = new Scores();
		
		sc.setType(type);
		sc.setPoint(10);
		
		return sc;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Teacher t1 = generateTeacher("teacher", 1);
		Teacher t2 = generateTeacher("teacher", 2);
		
		Student s1 = generateStudent("student", 1);
		Student s2 = generateStudent("student", 2);
		
		Grade g1 = generateGrade("homework", "g1");
		Grade g2 = generateGrade("quiz", "g2");
		g1.addScore(generateScore("homeowrk", 10));
		
		StudentMgntDaoInterface sDao = new StudentMgntDao();
		TeacherMgntDaoInterface tDao = new TeacherMgntDao();
		
		sDao.deleteStudentTable();
		tDao.deleteTeacherTable();
		
		sDao.addStudent(s1);
		sDao.addStudent(s2);
		
		tDao.addTeacher(t1);
		tDao.addTeacher(t2);
		tDao.addStudent(t1.getUserId(), "newStudent");
		tDao.addStudent(t1.getUserId(), "newStudent1");
		
		tDao.addCourse(t1.getUserId(), "course1", "alg");
		tDao.addCourse(t1.getUserId(), "course2", "alg1");
		tDao.removeCourse(t1.getUserId(), "course2", "alg1");
		
		s1.addGrade(g1);
		s1.addGrade(g2);
		
		sDao.addStudent(s1);
		
		
		
		
	}

}
