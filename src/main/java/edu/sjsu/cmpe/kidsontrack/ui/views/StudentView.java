package edu.sjsu.cmpe.kidsontrack.ui.views;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.kidsontrack.domain.Student;

public class StudentView extends View{
private final Student student;
	
	public StudentView(Student student) {
		super("student.mustache");
		this.student = student;
	}
	
	public Student getStudent() {
		return student;
	}
}
