package edu.sjsu.cmpe.kidsontrack.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.kidsontrack.domain.Teacher;

/**
 * @author Lei Zhang
 * 
 */
public class TeachersDto {
	private List<Teacher> teachers = new ArrayList<Teacher>();

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

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}



}
