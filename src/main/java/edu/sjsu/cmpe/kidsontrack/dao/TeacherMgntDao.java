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
import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.domain.TeacherStudents;


public class TeacherMgntDao {

	private static final Log log = LogFactory.getLog(TeacherMgntDao.class);
	private static MongoOperations op = new DBConfig().getDB();;

	
	public static void addTeacher(Teacher teacher)
	{
		op.save(teacher);
		
		Teacher t = op.findById(teacher.get_id(), Teacher.class);
			
		TeacherStudents ts = new TeacherStudents(); 
		ts.set_id(teacher.get_id());

		TeacherStudentMgntDao.addUpdate(ts);
		
		if(t == null)
			System.out.println("NULL");
		
		
		log.info("addTeacher: " + t.toString());
		
		
	}
	
	
	public static void addCourse(long id, Course course)
	{
		Teacher teacher = op.findById(id, Teacher.class);
		teacher.addCourse(course);
		
		op.save(teacher);
		log.info("addCourse: " + course.toString());
		
	}
	
	
	public static void removeCourse(long id, Course course)
	{
		Teacher teacher = op.findById(id, Teacher.class);
		teacher.removeCourse(course);
		
		op.save(teacher);
		log.info("removeCourse: " + course.toString());
		
	}
	
	
	
	public static void updateLastName(long id, String name)
	{
		Query query = Query.query(where("_id").is(id));
		
		 
		op.updateFirst(query,  update("lastName", name), Teacher.class);
		Teacher teacher = op.findById(id, Teacher.class);
		
		
		log.info("Updated lastName: " + teacher.toString());
		
		
	}
	
	
	public static void updateFirstName(long id, String name)
	{
		
		Query query = Query.query(where("_id").is(id));
		
		 
		op.updateFirst(query,  update("firstName", name), Teacher.class);
		Teacher teacher = op.findById(id, Teacher.class);
		
		log.info("Updated firstname: " + id + ": " + name);
		
	}
	
	
	public static void updateEmail(long id, String email)
	{
		
		Query query = Query.query(where("_id").is(id));
		
		 
		op.updateFirst(query,  update("email", email), Teacher.class);
		
		log.info("Updated email: " + id + ": " + email);
		
	}
	
	
	public static void updateAny(long id, String key, String value) 
	{
		Query query = Query.query(where("_id").is(id));
		
		op.updateFirst(query, update(key, value), Teacher.class);
		Teacher teacher = op.findById(id, Teacher.class);
		log.info("Updated " + key + ": " + id + ": " + value);
	}
	
	
	public static Teacher findById(long id)
	{
		Teacher teacher = op.findById(id, Teacher.class);
		log.info("Found: " + teacher);
		
		
		return teacher;
	}
	
	public static Teacher deleteTeacher(long id)
	{
		Teacher t = findById(id);
		
		op.remove(t);
		
		TeacherStudents record = op.findById(id, TeacherStudents.class);
		
		op.remove(record);
		
		return t;
		
	}
	
	
	
	public static List<Teacher> findAllTeachers()
	{
		List<Teacher> people = op.findAll(Teacher.class);
		log.info("Number of people = : " + people.size());
		
		return people;
		
	}
	
	
	public static boolean isFoundTeacher(Teacher teacher)
	{
		return (op.findById(teacher.get_id(), Teacher.class) == null) ? true : false;
	}
	
	
		
	public static void deleteTeacherTable()
	{
		op.dropCollection(Teacher.class);
	}
	
	


}