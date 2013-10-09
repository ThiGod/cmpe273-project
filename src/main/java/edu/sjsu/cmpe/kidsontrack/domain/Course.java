package edu.sjsu.cmpe.kidsontrack.domain;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;


public class Course {

	@Id
	private long _id;
	private String name;

	private static long count = 0;
	
	public Course()
	{
		count += 1;
		_id = count;

	}

	public long get_id() {
		return _id;
	}

=======
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * This is the course bean.
 * 
 * @author: Lei Zhang 
 * 
 * Creation date: (09/30/2013)
 */
@Document
@XmlRootElement(name = "courses")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the unique  id generated by MongoDB.
	 */
	@Id
	private String id;
	
	/**
	 * This is the sequential course Id generated by Kids On Track Application.
	 */
	private long courseId;

	/**
	 * This is the course name.
	 */
	@NotEmpty
	private String name;

	/**
	 * This is the course venue.
	 */
	private String venue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
>>>>>>> 61db1f2ba2addf65294a319a15fcd4f9c0a86b55

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	public static long getCount() {
		return count;
	}

	public String toString()
	{
		return "Course " + _id + " Name " + name + "\n";
		
	}
=======
	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	
>>>>>>> 61db1f2ba2addf65294a319a15fcd4f9c0a86b55
}
