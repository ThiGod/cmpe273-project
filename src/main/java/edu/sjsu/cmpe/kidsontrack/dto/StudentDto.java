package edu.sjsu.cmpe.kidsontrack.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.kidsontrack.domain.Student;

@JsonPropertyOrder(alphabetic = true)
public class StudentDto extends LinksDto {
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @param review
	 */
	public StudentDto(Student student) {
		super();
		this.student = student;
	}


}
