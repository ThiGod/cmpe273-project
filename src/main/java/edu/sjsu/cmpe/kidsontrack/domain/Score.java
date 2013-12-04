package edu.sjsu.cmpe.kidsontrack.domain;

public class Score {

	private String scoreId;
	private String type;
	private double point;
	
	public Score()
	{
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}
	
	

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public String toString()
	{
		StringBuffer str = new StringBuffer("\n{ type: " + type);
		str.append("\npoint: " + point + " }\n");
		
		return str.toString();
	}
	
	
}
