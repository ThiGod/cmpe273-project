package edu.sjsu.cmpe.kidsontrack.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.kidsontrack.domain.Teacher;

@JsonPropertyOrder(alphabetic = true)
public class TeacherDto extends LinksDto {
    private Teacher teacher;

    /**
     * @param teacher
     */
    public TeacherDto(Teacher teacher) {
	super();
	this.teacher = teacher;
    }

    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
	return teacher;
    }

    /**
     * @param teacher
     *            the teacher to set
     */
    public void setTeacher(Teacher teacher) {
	this.teacher = teacher;
    }
}
