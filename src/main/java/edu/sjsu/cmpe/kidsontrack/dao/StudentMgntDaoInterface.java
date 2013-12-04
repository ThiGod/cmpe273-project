package edu.sjsu.cmpe.kidsontrack.dao;

import java.util.HashMap;
import java.util.List;

import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Scores;
import edu.sjsu.cmpe.kidsontrack.domain.Student;


public interface StudentMgntDaoInterface {

	public Student addStudent(Student student);

	public Student findStudentById(String id);

	public List<Student> findAllStudents();

	public boolean updateLastName (String id, String lastName);

	public boolean updateFirstName(String id, String firstName);

	public boolean updateEmail(String id, String email);

	public boolean updateAny(String id, String key, String value);

	public Student deleteStudentById(String id);

	public void deleteStudentTable();
	
	public boolean addGrade(String studentId, Grade grade);
	
	public List<Grade> getAllGrades(String studentId);
	
	public List<Course> getAllCourses(String studentId);
	
	public boolean removeGrade(String studentId, Grade grade);
	
	public boolean addScore(String studentId, String courseId, Scores score);

	public boolean setScores(String studentId, String courseId, List<Scores> scores); 
		
	public boolean removeScore(String studentId, String courseId, Scores score);
	
	public HashMap getTotalPoint(String studentId);
	
	public boolean isFound(String email, String pwd);
}
