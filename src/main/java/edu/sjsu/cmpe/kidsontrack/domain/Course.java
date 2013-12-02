package edu.sjsu.cmpe.kidsontrack.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@XmlRootElement(name = "courses")
public class Course {


	private String courseId;
	
	@NotEmpty
	private String name;

	
	public Course()
	{

	}


	public String getCourseId() {
		return courseId;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}




	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
