package edu.sjsu.cmpe.kidsontrack.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.kidsontrack.domain.Student;

public class StudentsView extends View {
	private final List<Student> students;
	
	public StudentsView(List<Student> students) {
		super("students.mustache");
		this.students = students;
	}
	
	public List<Student> getStudents() {
		return students;
	}
}
