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
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;

public class StudentMgntDao {

	private static final Log log = LogFactory.getLog(StudentMgntDao.class);
	private static MongoOperations op = new DBConfig().getDB();;

	
	public static void addStudent(Student std)
	{
		op.save(std);
		log.info("Insert: " + std.toString());
		
		
	}
	
	public static void updateLastName(long id, String name)
	{
		Query query = Query.query(where("_id").is(id));
		
		 
		op.updateFirst(query,  update("lastName", name), Student.class);
		Student std = op.findById(id, Student.class);
		
		log.info("Updated lastName: " + id + ": " + name);
		
	}
	
	
	public static void updateFirstName(long id, String name)
	{
		
		Query query = Query.query(where("_id").is(id));
		
		 
		op.updateFirst(query,  update("firstName", name), Student.class);
		Student std = op.findById(id, Student.class);
		
		log.info("Updated firstname: " + id + ": " + name);
	}
	
	
	public static void updateEmail(long id, String email)
	{
		
		Query query = Query.query(where("_id").is(id));
		
		 
		op.updateFirst(query,  update("email", email), Student.class);
		Student std = op.findById(id, Student.class);
		
		log.info("Updated email: " + id + ": " + email);
	}
	
	
	
	public static Student findById(long id)
	{
		Student std = op.findById(id, Student.class);
		log.info("Found: " + std);
			
		return std;
	}
	
	public static Student deleteStudent(long id)
	{
		Student t = findById(id);
		
		op.remove(t);
		
		return t;
		
	}
	
		
	
	public static List<Student> findAllStudents()
	{
		List<Student> people = op.findAll(Student.class);
		log.info("Number of people = : " + people.size());
		
		return people;
		
	}
	
	
	public static void deleteStudentTable()
	{
		op.dropCollection(Student.class);
	}
	
	
	public static void updateAny(long id, String key, String value) 
	{
		Query query = Query.query(where("_id").is(id));
		
		op.updateFirst(query, update(key, value), Student.class);
		Student s = op.findById(id, Student.class);
		log.info("Updated " + key + ": " + id + ": " + value);
		}
	

}
