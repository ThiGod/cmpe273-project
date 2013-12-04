package edu.sjsu.cmpe.kidsontrack.ui.views;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.kidsontrack.domain.Teacher;

public class TeacherView extends View {
	private final Teacher teacher;
	
	public TeacherView(Teacher teacher) {
		super("teacher.mustache");
		this.teacher = teacher;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
}
