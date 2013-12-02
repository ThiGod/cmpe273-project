package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;

public class Grade {

	private String courseId;
	private List<Scores> scores = new ArrayList<Scores>();
	
	
	public Grade()
	{
		
	}
	
	
	public List<Scores> getScores() {
		return scores;
	}
	
	
	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}
	
	
	public boolean addScore(Scores score)
	{
		return scores.add(score);
	}
	
	
	public boolean removeScore(Scores score)
	{
		return scores.remove(score);
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	public String toString()
	{
		StringBuffer str = new StringBuffer("\n{ courseID: " + courseId + "\n");
		str.append("scores: {\n");
		for(Scores i: scores)
			str.append(i.toString());
		str.append(" }\n");
		
		return str.toString();
	}

	
}
