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


public class TeacherStudentMgntDao {

	private static final Log log = LogFactory.getLog(TeacherStudentMgntDao.class);
	private static MongoOperations op = new DBConfig().getDB();
	
	public static void addUpdate(TeacherStudents record)
	{
		op.save(record);
		
		TeacherStudents s = op.findById(record.get_id(), TeacherStudents.class);
		
		if (s == null)
			System.out.println("T/S NULL");
		
		log.info("addUpdate: " + record.toString());		
		
	}
	
	
	
	
	
	// mapping to the relational table
	public static void addStudent(long tID, Student std)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
			record.addStudent(std.get_id());
		op.save(record);
		
		record = op.findById(tID, TeacherStudents.class);
				
		if(op.findById(std.get_id(), Student.class) == null)
		{
			StudentMgntDao.addStudent(std);
		}
		
		log.info("addStudent: " + record.toString());		
		
	}
	
	
	public static void addStudentListID(long tID, List<Long> studentIDs)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
		record.setStudentIds(studentIDs);
		op.save(record);		
		
		log.info("addStudents: " + record.toString());		
		
	}
	
	
	public static void addStudentList(long tID, List<Student> students)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
		for(Student student: students)
		{
			record.addStudent(student.get_id());
			if(op.findById(student.get_id(), Student.class) == null)
			{
				StudentMgntDao.addStudent(student);
			}
			
			
		}
		
		op.save(record);
		log.info("addStudentByList: " + record.toString());
		
	}
	
	
	public static void removeStudent(long tID, long sID)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
		record.removeStudent(sID);
		
		op.save(record);
		
	}
	
	
	public static TeacherStudents findById(long id)
	{
		TeacherStudents ts = op.findById(id, TeacherStudents.class);
		log.info("Found: " + ts.toString());
		
		
		return ts;
	}
	
	public static void deleteTable()
	{
		op.dropCollection(TeacherStudents.class);
	}
	

}
