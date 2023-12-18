package com.hccs.project3.service;

import java.util.List;

import com.hccs.project3.model.Student;

public interface StudentService {
	Student save(Student student);
	
	List<Student>getStudents();
	
	Student update(Student student, long id);
	
	void delete(long id);
}
