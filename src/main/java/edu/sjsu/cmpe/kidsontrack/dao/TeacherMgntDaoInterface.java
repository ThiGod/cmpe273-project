package edu.sjsu.cmpe.kidsontrack.dao;

import java.util.List;

import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Scores;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;

public interface TeacherMgntDaoInterface {

	public Teacher addTeacher(Teacher teacher);

	public Teacher findTeacherById(String id);

	public List<Teacher> findAllTeachers();

	public boolean updateLastName (String id, String lastName);

	public boolean updateFirstName(String id, String firstName);

	public boolean updateEmail(String id, String email);

	public boolean updateAny(String id, String key, String value);

	public Teacher deleteTeacherById(String id);

	public void deleteTeacherTable();
	
	public void addCourse(String id, Course course);
	
//	public boolean addCourse(String teacherId, String courseId, String courseName); 
	
	public void removeCourse(String id, Course course);
	
//	public boolean removeCourse(String teacherId, String courseId, String courseName); 
	
	public boolean removeStudent(String teacherId, String studentId);
	
	public boolean removeAllStudents(String teacherId);
	
	public boolean addStudent(String teacherId, String studentId);
	
	public Student getHiStudentByCourse(String teacherId, String courseId);
	
	public boolean addScore(String teacherId, String studentId, String courseId, Scores score);
	
	public boolean removeScore(String teacherId, String studentId, String courseId, Scores score);
	
	public boolean addGrade(String teacherId, String studentId, Grade drade);
	
	public Course getCourseById(String teacherId, String courseId);
	
	public List<Course> getAllCourses(String teacherId);
	
	public void removeCourse(String teacherId, String courseId);
	
	public void removeAllCourses(String teacherId);
	
	public boolean isFound(String email, String pwd);
		
}
