package edu.sjsu.cmpe.kidsontrack.dao;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.Mongo;

import edu.sjsu.cmpe.kidsontrack.config.DBConfig;
import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Scores;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;



public class StudentMgntDao implements StudentMgntDaoInterface{

	private static final Log log = LogFactory.getLog(StudentMgntDao.class);
	private  MongoOperations op = new DBConfig().getDB();;

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
	public boolean addScore(String studentId, String courseId, Scores score) 
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
	public boolean removeScore(String studentId, String courseId, Scores score) 
	{
		Student student = op.findById(studentId, Student.class);
		
		if(student == null)
			return false;
		
		boolean valid = student.findGradeByCourseId(courseId).removeScore(score);
		
		op.save(student);
				
		log.info("removeScore: " + student.toString());
		
		return valid;
	}
	

	

	

}
