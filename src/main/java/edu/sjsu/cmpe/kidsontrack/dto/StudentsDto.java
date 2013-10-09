package edu.sjsu.cmpe.kidsontrack.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.kidsontrack.domain.Student;

/**
 * @author scott
 * 
 */
public class StudentsDto {
	private List<Student> students = new ArrayList<Student>();

	private List<LinkDto> links = new ArrayList<LinkDto>();

	public void addLink(LinkDto link) {
		links.add(link);
	}

	/**
	 * @return the links
	 */
	public List<LinkDto> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(List<LinkDto> links) {
		this.links = links;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}



}
