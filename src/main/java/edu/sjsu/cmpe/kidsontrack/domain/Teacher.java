package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User{

	private List<Course> courses = new ArrayList<Course>();
	
	private List<String> students = new ArrayList<String>();
	
	public Teacher()
	{
		
	}
	
	
	
	public List<String> getStudents() {
		return students;
	}



	public void setStudents(List<String> students) {
		this.students = students;
	}


	public List<Course> getCourses() {
		return courses;
	}

	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	

	public boolean removeStudent(String id)
	{
		return students.remove(id);
	}
	
	public boolean addStudent(String id)
	{
		return students.add(id);
	}

	

	public boolean addCourse(Course course) 
	{
		return courses.add(course);
	}
	
	public boolean removeCourse(Course course)
	{
		return courses.remove(course);
	}


	public String toString()
	{
		String str = "toString(): \n" + 
					 "{teacher: \n" + "{_id: " + getUserId() + "\n" +
				"firstName: " + getFirstName() + "\n" +
				"lastName: " + getLastName() + "\n" +
				"email: " + getEmail() + "\n";
	
		return str;
				
	}
}
