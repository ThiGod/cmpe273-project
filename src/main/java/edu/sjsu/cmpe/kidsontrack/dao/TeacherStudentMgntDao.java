package edu.sjsu.cmpe.kidsontrack.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private  MongoOperations op = new DBConfig().getDB();
	
	//@Autowired
	private StudentMgntDao studentMgntDao = new StudentMgntDao();
	
	public  void addUpdate(TeacherStudents record)
	{
		op.save(record);
		
		TeacherStudents s = op.findById(record.get_id(), TeacherStudents.class);
		
		if (s == null)
			System.out.println("T/S NULL");
		
		log.info("addUpdate: " + record.toString());		
		
	}
	
	
	
	
	
	// mapping to the relational table
	public void addStudent(long tID, Student std)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
			record.addStudent(std.getUserId());
		op.save(record);
		
		record = op.findById(tID, TeacherStudents.class);
				
		if(op.findById(std.getUserId(), Student.class) == null)
		{
			studentMgntDao.addStudent(std);
		}
		
		log.info("addStudent: " + record.toString());		
		
	}
	
	
	public  void addStudentListID(long tID, List<String> studentIDs)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
		record.setStudentIds(studentIDs);
		op.save(record);		
		
		log.info("addStudents: " + record.toString());		
		
	}
	
	
	public  void addStudentList(long tID, List<Student> students)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
		for(Student student: students)
		{
			record.addStudent(student.getUserId());
			if(op.findById(student.getUserId(), Student.class) == null)
			{
				studentMgntDao.addStudent(student);
			}
			
			
		}
		
		op.save(record);
		log.info("addStudentByList: " + record.toString());
		
	}
	
	
	public  void removeStudent(long tID, long sID)
	{
		TeacherStudents record = op.findById(tID, TeacherStudents.class);
		
		record.removeStudent(sID);
		
		op.save(record);
		
	}
	
	
	public  TeacherStudents findById(long id)
	{
		TeacherStudents ts = op.findById(id, TeacherStudents.class);
		log.info("Found: " + ts.toString());
		
		
		return ts;
	}
	
	public  void deleteTable()
	{
		op.dropCollection(TeacherStudents.class);
	}
	

}
