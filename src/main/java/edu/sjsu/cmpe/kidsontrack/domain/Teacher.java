package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User{

	private List<Course> courses = new ArrayList<Course>();
	
	private List<String> students = new ArrayList<String>();
	
	public Teacher()
	{
		
	}
	
	public boolean isStudentsEmpty()
	{
		return students.isEmpty();
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
		if(courses.isEmpty())
			return false;
		
		for(Course c: courses)
			if(c.getCourseId().equalsIgnoreCase(course.getCourseId()))
			{	
				courses.remove(c);
				return true;
			}
		
		return false;
	}
	
	public boolean removeCourse(String courseId)
	{
		if(courses.isEmpty())
			return false;
		
		Course c = getCourseById(courseId); 
		
		if(c!= null)		
		{	
			courses.remove(c);
			return true;
		}
		
		return false;
	}

	public void removeAllCourses()
	{
		if(courses.isEmpty())
			return;
		
		courses.clear();
	}
	
	
	public boolean foundCourse(String courseId)
	{
		boolean found = false;
		
		for(Course c: courses)
		{
			if(c.getCourseId().equalsIgnoreCase(courseId))
					found = true;
		}
		
		return found;
	}
	
	
	public Course getCourseById(String courseId)
	{
		for(Course c: courses)
		{
			if(c.getCourseId().equalsIgnoreCase(courseId))
					return c;
		}
		
		return null;
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
