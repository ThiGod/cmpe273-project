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
import edu.sjsu.cmpe.kidsontrack.domain.Scores;
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
//			Student s = op.findById(studentId, Student.class);
//			Grade grade = new Grade();
//			grade.setCourseId(course.getCourseId());
//			s.addGrade(grade);
//			op.save(s);
			
			Grade grade = new Grade();
			grade.setCourseId(course.getCourseId());
			
			(new StudentMgntDao()).addGrade(studentId, grade);
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
			
			if(!courses.isEmpty())
				s = Student.copyCourseId(courses);
			op.save(s);
		}
			
		if(!courses.isEmpty())
		{
			student.setGrades(Student.courseToGrade(courses));
			op.save(student);
		}
		
		log.info("AddStudent " + "student" + ": " + studentId );
		return true;		
	}

	
	public Student getHiStudentByCourse(String teacherId, String courseId)
	{
	
		Teacher t = op.findById(teacherId, Teacher.class);
		double max = -1;
		
		if(!t.foundCourse(courseId))
		{
			log.info("can't find the course taught by teacher: " + teacherId);
			return null;
		}
		
		List<String> studentId = t.getStudents();
		Student maxStudent = new Student();
		for(String id: studentId)
		{
			Student s = op.findById(id, Student.class);
			double current = s.findGradeByCourseId(courseId).getTotalPoint();
			
			if(max < current) {
				max = current;
				maxStudent = s;
			}	
		}
		
		return maxStudent;
	}

	
	public boolean addScore(String teacherId, String studentId, String courseId, Scores score) {
		Teacher teacher = op.findById(teacherId, Teacher.class);
		
		if(!teacher.foundCourse(courseId))
			return false;
		
		if(!teacher.getStudents().contains(studentId))
			return false;
		
		(new StudentMgntDao()).addScore(studentId, courseId, score);
	
		
		log.info("StudentId: " + studentId + "\naddGrade: " + courseId 
				+ "\naddScore: " + score.toString());

		return true;
	}
	

	public boolean removeScore(String teacherId, String studentId, String courseId, Scores score) {
		Teacher teacher = op.findById(teacherId, Teacher.class);
		
		if(!teacher.foundCourse(courseId))
			return false;
		
		if(!teacher.getStudents().contains(studentId))
			return false;
		
		(new StudentMgntDao()).removeScore(studentId, courseId, score);
	
		
		log.info("StudentId: " + studentId + "\nremoveGrade: " + courseId 
				+ "\nremoveScore: " + score.toString());

		return true;
	}

	
	public boolean addGrade(String teacherId, String studentId, Grade grade) {
		Teacher teacher = op.findById(teacherId, Teacher.class);
		
		if(!teacher.foundCourse(grade.getCourseId()))
			return false;
		
		if(!teacher.getStudents().contains(studentId))
			return false;
		
		(new StudentMgntDao()).setScores(studentId, grade.getCourseId(), grade.getScores());
	
		
		log.info("StudentId: " + studentId + "\naddGrade: " + grade.toString());

		return true;
	}


	

}
