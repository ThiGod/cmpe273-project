package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.Date;

public class AssignGrade extends Assignment{
	
	private int pointEarned = 0;	// by default
	
	
	public AssignGrade(Assignment assignment)
	{
		super();
		
		set_id(assignment.get_id());
		setDescription(assignment.getDescription());
		setDueDate(assignment.getDueDate());
		setTotalPoint(assignment.getTotalPoint());
		setPostDate(assignment.getPostDate());
			
	}


	public int getPointEarned() {
		return pointEarned;
	}


	public void setPointEarned(int pointEarned) {
		this.pointEarned = pointEarned;
	}
	
	
	public String toString() {
		
		String str = super.toString() + "pointEarned: " + pointEarned;
		
		return str;
	}
	

}
