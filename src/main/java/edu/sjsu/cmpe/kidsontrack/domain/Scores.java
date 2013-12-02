package edu.sjsu.cmpe.kidsontrack.domain;

public class Scores {

	private String type;
	private double point;
	
	public Scores()
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

	public String toString()
	{
		StringBuffer str = new StringBuffer("\n{ type: " + type);
		str.append("\npoint: " + point + " }\n");
		
		return str.toString();
	}
	
	
}
