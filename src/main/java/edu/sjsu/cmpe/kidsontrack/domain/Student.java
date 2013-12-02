package edu.sjsu.cmpe.kidsontrack.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document
@XmlRootElement(name = "students")
public class Student extends User {

	//@Id
	//private String studentId;
	private List<Grade> grades = new ArrayList<Grade>();
	
	public Student()
	{
		
	}
	
	
//	
//	public String getStudentId() {
//		return studentId;
//	}
//
//
//
//	public void setStudentId(String studentId) {
//		this.studentId = studentId;
//	}



	public List<Grade> getGrades() {
		return grades;
	}



	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	public boolean addGrade(Grade gr)
	{
		return grades.add(gr);
	}
	
	public Grade findGradeByCourseId(String courseId)
	{
		for (Grade g: grades)
			if(g.getCourseId().equals(courseId))
				return g;
		
		return null;
	}
	
	public double totalPointForEachCourse(String courseId)
	{
		Grade g = findGradeByCourseId(courseId);
		return g.getTotalPoint();
	}
	
	public boolean removeGrade(Grade gr)
	{
		return grades.remove(gr);
	}

	public boolean removeAllGrades()
	{
		grades.clear();
		
		return (grades.isEmpty());
	}


	public String toString()
	{
		StringBuffer str = new StringBuffer("{student: \n" + "{_id: " + getUserId() + "\n" +
			"firstName: " + getFirstName() + "\n" +
			"lastName: " + getLastName() + "\n" +
			"email: " + getEmail() + "\n");
		
		for(Grade g: grades)
			str.append(g.toString());
	
		str.append("}\n");
		
		return str.toString();
				
	}
	
	
	public static Student copyCourseId(List<Course> courses)
	{
		Student student = new Student();
		Grade grade = new Grade();
		//student.setGrades(null);
		for(Course c: courses)
		{
			grade.setCourseId (c.getCourseId());
			student.addGrade(grade);
		}
		
		return student;
	}
	
	public static List<Grade> courseToGrade(List<Course> courses)
	{
		List<Grade> gr = new ArrayList<Grade>();
		
		for(Course c: courses)
		{
			Grade grade = new Grade();
			grade.setCourseId(c.getCourseId());
			gr.add(grade);
		}
		
		return gr;
	}
	

	public String printGrades()
	{
		String str = "";
		
		str = "Student Id: " + getUserId() + "\n";
		
		for(Grade gr: grades)
		{
			str += "Course Id: " + gr.getCourseId() + "\n";
			str += "Total point: " + gr.getTotalPoint() + "\n";
		}		
		
		return str;
	}
//	
//	public Grade findGradeByCourseId(String id)
//	{
//		for(Grade g: grades)
//			if(g.getCourseId().equalsIgnoreCase(id))
//				return g;
//		
//		return null;
//	}
	
}
