package edu.sjsu.cmpe.kidsontrack.domain;

<<<<<<< HEAD
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
	

	
=======
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@XmlRootElement(name = "students")
public class Student extends User{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This is the sequtial student Id generated by Kids On Track Application.
	 */
	//private long studentId;



	

>>>>>>> 61db1f2ba2addf65294a319a15fcd4f9c0a86b55
}
