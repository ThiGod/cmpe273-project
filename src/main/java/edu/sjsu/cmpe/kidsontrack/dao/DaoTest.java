package edu.sjsu.cmpe.kidsontrack.dao;

import java.util.List;

import edu.sjsu.cmpe.kidsontrack.domain.Course;
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
		t.setFirstName("name" +id+ num);
		t.setLastName("last"+ id+num);
		t.setEmail("email"+ id+num);
		t.setPassword("password" + id+num);
		
		return t;
		
	}
	
	public static Student generateStudent(String id, int num)
	{
		Student t = new Student();
		
		t.setUserId(id + num);
		t.setFirstName("name" + id+num);
		t.setLastName("last"+ id+num);
		t.setEmail("email"+ id+ num);
		t.setPassword("password" + id+ num);
		
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
	
	public static Course generateCourse(String id, String name)
	{
		Course c = new Course();
		c.setCourseId(id);
		c.setName(name);
		return c;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Teacher t1 = generateTeacher("teacher", 1);
		Teacher t2 = generateTeacher("teacher", 2);
		
		Student s1 = generateStudent("student", 1);
		Student s2 = generateStudent("student", 2);
		
		Course c1 = generateCourse("course1", "course1");
		Course c2 = generateCourse("course2", "course2");
		
		Grade g1 = generateGrade("homework!!!", "g1");
		Grade g2 = generateGrade("quiz", "g2");
		g1.addScore(generateScore("homeowrk", 10));
		g1.addScore(generateScore("homeowrk1", 10));
		g1.addScore(generateScore("homeowrk2", 10));
		
		StudentMgntDaoInterface sDao = new StudentMgntDao();
		TeacherMgntDaoInterface tDao = new TeacherMgntDao();
		
		sDao.deleteStudentTable();
		tDao.deleteTeacherTable();
		
		sDao.addStudent(s1);
		sDao.addStudent(s2);
		
		tDao.addTeacher(t1);
		tDao.addTeacher(t2);
//		tDao.addStudent(t1.getUserId(), "newStudent");
//		tDao.addStudent(t1.getUserId(), "newStudent1");
		tDao.addStudent(t1.getUserId(), s1.getUserId());
		tDao.addStudent(t1.getUserId(), s2.getUserId());
		
//		tDao.addCourse(t1.getUserId(), "course1", "alg");
//		tDao.addCourse(t1.getUserId(), "course2", "alg1");
//		tDao.removeCourse(t1.getUserId(), "course2", "alg1");
//		
		tDao.addCourse(t1.getUserId(), c1);
		tDao.addCourse(t1.getUserId(), c2);
		
		tDao.addScore(t1.getUserId(),s1.getUserId(), c1.getCourseId(), generateScore("hw1", 10));
		tDao.addScore(t1.getUserId(),s1.getUserId(), c1.getCourseId(), generateScore("hw2", 10));
		
		tDao.addScore(t1.getUserId(),s2.getUserId(), c1.getCourseId(), generateScore("hw2", 8));
		tDao.addScore(t1.getUserId(),s2.getUserId(), c1.getCourseId(), generateScore("hw1", 8));
		
		
		g1.setCourseId(c1.getCourseId());
		tDao.addGrade(t1.getUserId(),s2.getUserId(), g1);
		
		Scores sc = g1.getScores().get(0);
//		System.out.println(sc.getType());
//		
//		tDao.removeScore(t1.getUserId(),s2.getUserId(), c1.getCourseId(), sc);
//		tDao.removeStudent(t1.getUserId(),s2.getUserId());

//		tDao.removeAllStudents(t1.getUserId());
		
		//tDao.removeCourse(t1.getUserId(), c1.getCourseId());
		
//		Course c = tDao.getCourseById(t1.getUserId(), c1.getCourseId());
//		
//		if(c==null)
//			System.out.println("return course FAILED");
//		System.out.println(c.toString());
//		
		
//		List<Course> courses = tDao.getAllCourses(t1.getUserId());
//		
//		for(Course c: courses)
//			System.out.println(c.toString());
		
//		sDao.addGrade(s1.getUserId(),g1);
//		sDao.addGrade(s1.getUserId(),g2);
//		sDao.addGrade(s2.getUserId(),g1);
//		
//		Student s = tDao.getHiStudentByCourse(t1.getUserId(), c1.getCourseId());
//		System.out.println("highest grade: " + s.getUserId() + " for course: " + c1.getCourseId());
//////		
		//System.out.println(s1.printGrades());
		//System.out.println(g1.toString());
		//System.out.println("remove score: " + g1.removeScorebyName("homeowrk2"));
		
//		sDao.addStudent(s1);
		
//		tDao.removeCourse(t1.getUserId(), c1.getCourseId());

//		tDao.removeAllCourses(t1.getUserId());
		
//		boolean found = tDao.isFound(t2.getEmail(), t1.getPassword());
//		System.out.println("match: " + found);
		
		boolean found = sDao.isFound(s2.getEmail(), s1.getPassword());
		System.out.println("match: " + found);
		
	}

}