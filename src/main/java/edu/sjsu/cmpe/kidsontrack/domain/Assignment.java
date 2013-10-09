package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Assignment {
	
	private long _id;
	private String description;
	private Date dueDate;
	private int totalPoint;
	private Date postDate;
	private static long count = 0;
	
	public Assignment()
	{
		
		count += 1;
		_id = count;
		postDate = new Date();
		
	}


	public long get_id() {
		return _id;
	}


	public void set_id(long _id) {
		this._id = _id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String descript) {
		this.description = descript;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public int getTotalPoint() {
		return totalPoint;
	}


	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}


	public Date getPostDate() {

		return postDate;
	}


	//get current date time with Date()
	public void setPostDate(Date date) {
		
		this.postDate = date;
	}
	
	
	public String toString() {
		String str = "{Assignment: {\n" +
				"_id: " + _id + "\n" +
				"description: " + description + "\n" +
				"totalPoint: " + totalPoint + "\n";
		
		return str;
				
	}
	
	

}
