package edu.sjsu.cmpe.kidsontrack.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import edu.sjsu.cmpe.kidsontrack.domain.Teacher;


public final class TeacherRepository {
    private static Map<String, Teacher> teacherRepository = new LinkedHashMap<String, Teacher>();
   
    private TeacherRepository() {} 

    
    public static Map<String, Teacher> getTeacherRepository(){
        return teacherRepository;
      }
}