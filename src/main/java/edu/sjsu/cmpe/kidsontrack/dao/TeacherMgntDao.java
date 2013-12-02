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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import edu.sjsu.cmpe.kidsontrack.config.DBConfig;
import edu.sjsu.cmpe.kidsontrack.domain.Course;
import edu.sjsu.cmpe.kidsontrack.domain.Grade;
import edu.sjsu.cmpe.kidsontrack.domain.Student;
import edu.sjsu.cmpe.kidsontrack.domain.Teacher;

@Repository
public class TeacherMgntDao implements TeacherMgntDaoInterface{

	private static final Log log = LogFactory.getLog(TeacherMgntDao.class);
	private MongoOperations op = new DBConfig().getDB();;


	@Override
	public Teacher addTeacher(Teacher teacher) 
	{
		op.save(teacher);
		log.info("Update or Insert: " + teacher.toString());

		return teacher;
	}

	@Override
	public Teacher findTeacherById(String id) 
	{
		Teacher teacher = op.findById(id, Teacher.class);
		log.info("Found: " + teacher);

		return teacher;	
	}

	public List<Teacher> findAllTeachers() {
		List<Teacher> people = op.findAll(Teacher.class);
		log.info("Number of people = : " + people.size());

		return people;

	}


	@Override
	public boolean updateLastName(String id, String lastName) {
		Query query = Query.query(where("_id").is(id));

		op.updateFirst(query,  update("lastName", lastName), Teacher.class);
		Teacher teacher = op.findById(id, Teacher.class);

		log.info("Updated teacher lastName: " + id + ": " + lastName);

		// return true if updated, else false
		return ( (teacher!=null) ? true: false);

	}

	@Override
	public boolean updateFirstName(String id, String firstName) {
		Query query = Query.query(where("_id").is(id));

		op.updateFirst(query,  update("firstName", firstName), Teacher.class);
		Teacher teacher = op.findById(id, Teacher.class);

		log.info("Updated teacher firstname: " + id + ": " + firstName);

		// return true if updated, else false
		return ( (teacher!=null) ? true: false);
	}

	@Override
	public boolean updateEmail(String id, String email) 
	{
		Query query = Query.query(where("_id").is(id));

		op.updateFirst(query,  update("email", email), Teacher.class);
		Teacher teacher = op.findById(id, Teacher.class);

		log.info("Updated teacher email: " + id + ": " + email);

		// return true if updated, else false
		return ( (teacher!=null) ? true: false);

	}

	@Override
	public boolean updateAny(String id, String key, String value) {
		Query query = Query.query(where("_id").is(id));

		op.updateFirst(query, update(key, value), Teacher.class);
		Teacher t = op.findById(id, Teacher.class);
		log.info("Updated " + key + ": " + id + ": " + value);

		// return true if updated, else false
		return ( (t!=null) ? true: false);
	}

	@Override
	public Teacher deleteTeacherById(String id) 
	{
		Teacher teacher = op.findById(id, Teacher.class);
		
		List<String> students = teacher.getStudents();
		
		for(String studentId: students)
		{
			Student student = op.findById(studentId, Student.class);
			student.removeAllGrades();
			op.save(student);
		}
		
		log.info("remove all grades from students and delete teacher " + teacher.toString());		
		
		op.remove(teacher);

		return teacher;
	}

	public void deleteTeacherTable() {
		op.dropCollection(Teacher.class);
	}


	public void addCourse(String id, Course course) {
		Teacher teacher = op.findById(id, Teacher.class);
		teacher.addCourse(course);

		op.save(teacher);
		
		List<String> students = teacher.getStudents();
		
		for(String studentId: students)
		{
			Student s = op.findById(studentId, Student.class);
			Grade grade = new Grade();
			grade.setCourseId(course.getCourseId());
			s.addGrade(grade);
			op.save(s);
		}
		
		log.info("addCourse: " + course.toString());

	}

	public boolean addCourse(String teacherId, String courseId, String courseName) 
	{
		Query query = Query.query(where("_id").is(teacherId));
		Update update = new Update();
		
		DBObject updateCourse = BasicDBObjectBuilder.start()
                .add("courseId", courseId)
                .add("name", courseName).get();
		
		update.addToSet("courses", updateCourse);
		op.updateFirst(query, update, Teacher.class);
		
		Teacher t = op.findById(teacherId, Teacher.class);
		
		log.info("Updated " + "courses" + ": " + courseId + ":" + courseName );

		// return true if updated, else false
		return ( (t!=null) ? true: false);

	}
	
	
	public void removeCourse(String id, Course course) {
		Teacher teacher = op.findById(id, Teacher.class);
		teacher.removeCourse(course);
		op.save(teacher);
		
		List<String> students = teacher.getStudents();
		
		for(String studentId: students)
		{
			Student s = op.findById(studentId, Student.class);
			s.removeGrade(s.findGradeByCourseId(course.getCourseId()));
			op.save(s);
		}
		
		log.info("removeCourse: " + course.toString());

	}



	public boolean removeCourse(String teacherId, String courseId, String courseName) 
	{
		Query query = Query.query(where("_id").is(teacherId));
		Update update = new Update();
		
		DBObject updateCourse = BasicDBObjectBuilder.start()
                .add("courseId", courseId)
                .add("name", courseName).get();
		
		update.pull("courses", updateCourse);
		op.updateFirst(query, update, Teacher.class);
		
		Teacher t = op.findById(teacherId, Teacher.class);
		
		log.info("Updated " + "courses" + ": " + courseId + ":" + courseName );

		// return true if updated, else false
		return ( (t!=null) ? true: false);

	}

	public boolean isFoundTeacher(Teacher teacher) {
		return (op.findById(teacher.getUserId(), Teacher.class) == null) ? true
				: false;
	}

	/* Teacher won't remove the student from the DB.
	** Teach can only remove the student from the students list
	*/
	@Override
	public boolean removeStudent(String teacherId, String studentId) {
		Query query = Query.query(where("_id").is(teacherId));
		
		// remove the student from the students list
		Update update = new Update();
		update.pull("students", studentId);
		op.updateFirst(query, update, Teacher.class);
		
		Teacher t = op.findById(teacherId, Teacher.class);
		
		Student student = op.findById(studentId, Student.class);
		
		// remove all the grades from the student
		student.removeAllGrades();
		op.save(student);
		
		log.info("removestudent " + "student" + ": " + studentId );

		// return true if updated, else false
		return ( (t!=null) ? true: false);

	}

	
	/* Teacher adds the student to the students list 
	** and add to the DB if it's not existing
	*/
	@Override
	public boolean addStudent(String teacherId, String studentId) {
		Query query = Query.query(where("_id").is(teacherId));
		
		if(query == null)
			return false;
		
		Update update = new Update();
		update.addToSet("students", studentId);
		op.updateFirst(query, update, Teacher.class);
		
		Student student = op.findById(studentId, Student.class);
		
		Teacher t = op.findById(teacherId, Teacher.class);
		List<Course> courses = t.getCourses();
		
		if(student == null)
		{
			Student s = new Student();
			s.setUserId(studentId);			
			s = Student.copyCourseId(courses);
			op.save(s);
		}
		
		student.setGrades(Student.courseToGrade(courses));
		op.save(student);
		
		log.info("AddStudent " + "student" + ": " + studentId );
		return true;		
	}




}
