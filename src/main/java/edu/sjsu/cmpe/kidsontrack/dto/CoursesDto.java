package edu.sjsu.cmpe.kidsontrack.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.kidsontrack.domain.Course;

/**
 * @author scott
 * 
 */
public class CoursesDto {
	private List<Course> reviews = new ArrayList<Course>();

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

	public List<Course> getReviews() {
		return reviews;
	}

	public void setReviews(List<Course> reviews) {
		this.reviews = reviews;
	}



}
