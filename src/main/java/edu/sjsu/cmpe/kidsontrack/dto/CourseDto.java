package edu.sjsu.cmpe.kidsontrack.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.kidsontrack.domain.Course;

@JsonPropertyOrder(alphabetic = true)
public class CourseDto extends LinksDto {
	private Course course;

	/**
	 * @param review
	 */
	public CourseDto(Course course) {
		super();
		this.course = course;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}



}
