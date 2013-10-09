package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.Date;

public class Document {

	private long _id;
	private String name;
	private Date postDate;
	
	
	public Document()
	{
		
	}


	public long get_id() {
		return _id;
	}


	public void set_id(long _id) {
		this._id = _id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getPostDate() {
		return postDate;
	}


	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
	
	
}
