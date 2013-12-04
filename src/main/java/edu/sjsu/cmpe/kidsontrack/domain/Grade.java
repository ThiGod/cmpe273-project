package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;

public class Grade {

	private String courseId;
	private List<Score> scores = new ArrayList<Score>();
	
	
	public Grade()
	{
		
	}
	
	
	public List<Score> getScores() {
		return scores;
	}
	
	
	public boolean setScores(List<Score> scores) {
		
		if(scores.isEmpty())
			return false;
		
		this.scores = scores;
		return true;
	}
	
	
	public boolean addScore(Score score)
	{
		return scores.add(score);
	}
	
	
	public boolean removeScore(Score score)
	{
		return scores.remove(score);
	}
	
	
	public boolean removeScore(String name)
	{
		for(int i = 0; i < scores.size(); i++)
			if(scores.get(i).getType().equalsIgnoreCase(name))
			{
				scores.remove(i);
				return true;
			}	
		return false;
	}
	
	
	
	public String getCourseId() {
		return courseId;
	}
	
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	public double getTotalPoint()
	{
		double total = 0;
		
		for(Score sc: scores)
		{
			total+= sc.getPoint();
		}
		
		return total;
	}
	
	
	public boolean removeScorebyName(String name)
	{
		if(scores.isEmpty())
			return false;
		
		for(int i= scores.size()-1; i > -1; i--)
		{	
			if(scores.get(i).getType().equalsIgnoreCase(name))
				scores.remove(i);
		}
		
		return true;
	}
	
	public String toString()
	{
		StringBuffer str = new StringBuffer("\n{ courseID: " + courseId + "\n");
		str.append("scores: {\n");
		for(Score i: scores)
			str.append(i.toString());
		str.append(" }\n");
		
		return str.toString();
	}

	
}
