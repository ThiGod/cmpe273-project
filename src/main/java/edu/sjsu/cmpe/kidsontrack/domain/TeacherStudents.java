package edu.sjsu.cmpe.kidsontrack.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;


public class TeacherStudents {

	@Id
	private long _id;
	private List<Long> studentIds = new ArrayList<Long>();
	
	
	public TeacherStudents()
	{

	}		
	
	
	public long get_id() {
		return _id;
	}


	public List<Long> getStudentIds() {
		return studentIds;
	}




	public void set_id(long _id) {
		this._id = _id;
	}


	public void setStudentIds(List<Long> studentIds) {
		this.studentIds = studentIds;
	}



	public void addStudent(long sID)
	{
		studentIds.add(sID);
	}
	
	public void removeStudent(long sID)
	{
		studentIds.remove(sID);
	}
	
	public void removeAllStudents()
	{
		studentIds.clear();
	}
	
	
	public String toString() 
	{
		String str = "{T/S: " + "_id: " + _id + " ";
		
		for(long id: studentIds)
			str += " student " + id +":";
		
		return str;
				
				
	}
	
	
}
