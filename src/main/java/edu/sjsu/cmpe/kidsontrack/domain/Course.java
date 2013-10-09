package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;


public class Course {

	@Id
	private long _id;
	private String name;

	private static long count = 0;
	
	public Course()
	{
		count += 1;
		_id = count;

	}

	public long get_id() {
		return _id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getCount() {
		return count;
	}

	public String toString()
	{
		return "Course " + _id + " Name " + name + "\n";
		
	}
}
