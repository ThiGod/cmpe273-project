package edu.sjsu.cmpe.kidsontrack.dao;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

import edu.sjsu.cmpe.kidsontrack.config.DBConfig;
import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Score;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;



public class StudentMgntDao implements StudentMgntDaoInterface{

	private static final Log log = LogFactory.getLog(StudentMgntDao.class);
	private  MongoOperations op = new DBConfig().getDB();;
	
	public String authenticate(String email, String pwd) {
		Query query = Query.query(where("email").is(email)).addCriteria(where("password").is(pwd));
		
		List<Student> t = op.find(query, Student.class);
		
		System.out.println("size" + t.size());
		
		String uid = null;
		
		if(t.size() == 1) 
			uid = t.get(0).getUserId();
		
		return uid;
	}
	
	public Student addStudent(Student student)
	{
		op.save(student);
		log.info("Update or Insert: " + student.toString());
		
		return student;
		
	}

	public Student findStudentById(String id)
	{
		Student std = op.findById(id, Student.class);
		
		log.info("Found: " + std);
			
		return std;
		
	}

	public List<Student> findAllStudents()
	{
		List<Student> people = op.findAll(Student.class);
		log.info("Number of people = : " + people.size());
		
		return people;
		
	}

	public boolean updateLastName (String id, String lastName)
	{
		Query query = Query.query(where("_id").is(id));
	
		op.updateFirst(query,  update("lastName", lastName), Student.class);
		Student std = op.findById(id, Student.class);

		log.info("Updated student lastName: " + id + ": " + lastName);
		
		// return true if updated, else false
		return ( (std!=null) ? true: false);
	}

	
	public boolean updateFirstName(String id, String firstName)
	{
		Query query = Query.query(where("_id").is(id));
		
		op.updateFirst(query,  update("firstName", firstName), Student.class);
		Student std = op.findById(id, Student.class);

		log.info("Updated student firstname: " + id + ": " + firstName);
		
		// return true if updated, else false
		return ( (std!=null) ? true: false);
	}

	
	public boolean updateEmail(String id, String email)
	{
		Query query = Query.query(where("_id").is(id));
			 
		op.updateFirst(query,  update("email", email), Student.class);
		Student std = op.findById(id, Student.class);
		
		log.info("Updated student email: " + id + ": " + email);
		
		// return true if updated, else false
		return ( (std!=null) ? true: false);
	}

	
	// use this function to update any field
	public boolean updateAny(String id, String key, String value)
	{
		Query query = Query.query(where("_id").is(id));
		
		op.updateFirst(query, update(key, value), Student.class);
		Student s = op.findById(id, Student.class);
		log.info("Updated " + key + ": " + id + ": " + value);
		
		// return true if updated, else false
		return ( (s!=null) ? true: false);
		
	}

	public Student deleteStudentById(String id)
	{
		Student std = op.findById(id, Student.class);
		op.remove(std);
		
		return std;
		
	}

	public void deleteStudentTable()
	{
		op.dropCollection(Student.class);
	}

	@Override
	public boolean addGrade(String studentId, Grade grade) {
		Student student = op.findById(studentId, Student.class);
		
		if(student == null)
			return false;
		
		student.addGrade(grade);

		op.save(student);
		
		log.info("addCourse: " + grade.toString());
		
		return true;
	}

	
	public List<Grade> getAllGrades(String studentId)
	{
		Student student = op.findById(studentId, Student.class);
		
		if(student == null)
		{	
			log.info("getAllGrades: student isn't found");
			return null;	
		}
		
		return student.getGrades();
	}
	
	
	public List<Course> getAllCourses(String studentId)
	{
		List<Course> courses = new ArrayList<Course>();
		
		Student student = op.findById(studentId, Student.class);
		
		String stdId = student.getUserId();
		
		Query query = Query.query(where("students").is(stdId));
		List<Teacher> teachers = op.find(query, Teacher.class);
		
		int size = teachers.size();
		
		System.out.println("size of teacher:" + size);
		
		for(int i = 0; i < teachers.size(); i++)
		{
			courses = teachers.get(i).getCourses();
		}
		
//		List<Grade> grades = student.getGrades();
//		
//		for(int i = 0; i < grades.size(); i++)
//		{	
//			String courseId = grades.get(i).getCourseId();
//			
//			Query query = Query.query(where("courses.courseId").is(courseId));
//			
////			Query query = Query.query(where("email").is(email))
////					.addCriteria(where("password").is(pwd));
//		
////			List<Student> t = op.find(query, Student.class);
//		
//		}
		return courses;
	}
	
	
	public boolean isFound(String email, String pwd)
	{
		boolean found = false;
		Query query = Query.query(where("email").is(email)).addCriteria(where("password").is(pwd));
		
		List<Student> t = op.find(query, Student.class);
		
		System.out.println("size" + t.size());
		
		if(t.size() > 0)
			found = true;
		
		return found;
	}
	


	@Override
	public boolean removeGrade(String studentId, Grade grade) 
	{
		Student student = op.findById(studentId, Student.class);
		
		if(student == null)
			return false;
		
		student.removeGrade(grade);
		op.save(student);
		log.info("removeCourse: " + grade.toString());
		
		return true;
	}

	@Override
	public boolean addScore(String studentId, String courseId, Score score) 
	{
		Student student = op.findById(studentId, Student.class);
		
		if(student == null)
			return false;
		
		boolean valid = student.findGradeByCourseId(courseId).addScore(score);
		
		op.save(student);
				
		log.info("addScore: " + student.toString());
		
		return valid;
	}

	@Override
	public boolean setScores(String studentId, String courseId, List<Score> scores) 
	{
		Student student = op.findById(studentId, Student.class);
		
		if(student == null)
			return false;
		
		boolean valid = student.findGradeByCourseId(courseId).setScores(scores);
		
		op.save(student);
				
		log.info("addScore: " + student.toString());
		
		return valid;
	}
	
	
	@Override
	public boolean removeScore(String studentId, String courseId, Score score) 
	{
		Student student = op.findById(studentId, Student.class);
		
		if(student == null)
			return false;
		
		//boolean valid = student.findGradeByCourseId(courseId).removeScore(score);
		boolean valid = student.findGradeByCourseId(courseId).removeScore(score.getType());
		
		
		op.save(student);
				
		log.info("removeScore: " + student.toString() + "result: " + valid);
		
		return valid;
	}
	
	
	public HashMap getTotalPoint(String studentId)
	{
		HashMap<String, Double> tables = new HashMap<String, Double>();
		
		Student student = op.findById(studentId, Student.class);
		
		List<Grade> grades = student.getGrades();
		
		for(Grade g: grades)
			tables.put(g.getCourseId(), g.getTotalPoint());
				
		return tables;
	}
	

	

	

}
