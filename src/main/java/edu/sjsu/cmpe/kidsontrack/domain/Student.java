package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;

public class Student extends User {

	@Id
	private long _id;
	private static long count = 200;
	
	public Student()
	{
		super();
		count += 1;
		_id = count;
		
	}


	public long get_id() {
		return _id;
	}

	
	public static long getCount()
	{
		return count;
	}
	

	public String toString()
	{
		String str ="{student: \n" + "{_id: " + _id + "\n" +
			"firstName: " + getFirstName() + "\n" +
			"lastName: " + getLastName() + "\n" +
			"email: " + getEmail() + "\n";
	
		return str;
				
	}
	

	
}
