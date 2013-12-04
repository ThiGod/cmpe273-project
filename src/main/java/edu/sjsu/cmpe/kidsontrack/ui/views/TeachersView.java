package edu.sjsu.cmpe.kidsontrack.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.kidsontrack.domain.Teacher;

public class TeachersView extends View {
	private final List<Teacher> teachers;
	
	public TeachersView(List<Teacher> teachers) {
		super("teachers.mustache");
		this.teachers = teachers;
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
}
