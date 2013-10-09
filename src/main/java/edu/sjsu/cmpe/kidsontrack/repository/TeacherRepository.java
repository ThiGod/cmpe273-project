package edu.sjsu.cmpe.kidsontrack.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import edu.sjsu.cmpe.kidsontrack.domain.Teacher;


public final class TeacherRepository {
    private static Map<Long, Teacher> teacherRepository = new LinkedHashMap<Long, Teacher>();
   
    private TeacherRepository() {} 

    
    public static Map<Long, Teacher> getTeacherRepository(){
        return teacherRepository;
      }
}