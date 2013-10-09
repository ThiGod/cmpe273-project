package edu.sjsu.cmpe.kidsontrack.domain;

public class Classes {
	
	private long _id;
	private String name;
	//private String gradeLevel;
	//private String room;
	private static long count = 0;
	
	public Classes()
	{
		count += 1;
		_id = count;

	}


	public long get_id() {
		return _id;
	}


	/*
	public void set_id(long _id) {
		this._id = _id;
	}

	*/
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

/*
	public String getGradeLevel() {
		return gradeLevel;
	}


	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}
	
*/	
	

}
