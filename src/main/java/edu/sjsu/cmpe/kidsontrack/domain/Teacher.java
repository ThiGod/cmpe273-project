package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


import org.springframework.data.mongodb.core.mapping.Document;


@Document
@XmlRootElement(name = "teachers")
public class Teacher extends User{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This is the sequential teacher Id generated by Kids On Track Application.
	 */
	//private long teacherId;
	
	private List<Student> students = new ArrayList<Student>();

	private List<Course> courses = new ArrayList<Course>();


	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	

}
