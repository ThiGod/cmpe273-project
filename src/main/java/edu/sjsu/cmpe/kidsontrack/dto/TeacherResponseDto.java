package edu.sjsu.cmpe.kidsontrack.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.kidsontrack.domain.Teacher;
import edu.sjsu.cmpe.kidsontrack.domain.User;



/**
 * @author scott
 * 
 */
public class TeacherResponseDto extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// This is the list of students URI
	private List<LinkDto> students = new ArrayList<LinkDto>();
	
	// This is the list of courses URI
	private List<LinkDto> courses = new ArrayList<LinkDto>();
	
	private List<LinkDto> links = new ArrayList<LinkDto>();

	public TeacherResponseDto(Teacher teacher) {

		this.setUserId(teacher.getUserId());
		this.setFirstName(teacher.getFirstName());	
		this.setLastName(teacher.getLastName());
		this.setEmail(teacher.getEmail());
	}

	public List<LinkDto> getStudents() {
		return students;
	}

	public void setStudents(List<LinkDto> students) {
		this.students = students;
	}

	public List<LinkDto> getCourses() {
		return courses;
	}

	public void setCourses(List<LinkDto> courses) {
		this.courses = courses;
	}

	public List<LinkDto> getLinks() {
		return links;
	}

	public void setLinks(List<LinkDto> links) {
		this.links = links;
	}
	
	public void addLink(LinkDto link) {
		links.add(link);
	}

	
}
