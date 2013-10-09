package edu.sjsu.cmpe.kidsontrack.domain;

import edu.sjsu.cmpe.kidsontrack.config.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;


public class Teacher extends User{
	
	@Id
	private long _id;
	private List<Course> courses = new ArrayList<Course>();
	private static long count = 0;
	
	public Teacher()
	{
		super();
		count += 1;
		_id = count;
	}


	public long get_id() {
		return _id;
	}

	
	public static long getCount()
	{
		return count;
	}
	
	
	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	
	public void addCourse(Course course)
	{
		courses.add(course);
	}
	
	
	public void removeCourse(Course course)
	{
		courses.remove(course);
		
	}

	public String toString()
	{
		String str = "toString(): \n" + 
					 "{teacher: \n" + "{_id: " + _id + "\n" +
				"firstName: " + getFirstName() + "\n" +
				"lastName: " + getLastName() + "\n" +
				"email: " + getEmail() + "\n";
		
		for(Course course: courses)
			str += course.toString();

		return str;
				
	}
	

}
