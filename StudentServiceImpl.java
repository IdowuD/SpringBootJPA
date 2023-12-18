package com.hccs.project3.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hccs.project3.model.Student;
import com.hccs.project3.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired 
	private StudentRepository studentRepo;

	@Override
	public Student save(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student update(Student newStudent, long id) {
		Student student = studentRepo.findById(id).get();
		
		 if (Objects.nonNull(newStudent.getFirst_name())
	                && !"".equalsIgnoreCase(
	                newStudent.getFirst_name())) {
	            student.setFirst_name(newStudent.getFirst_name());
	            student.setEmail(newStudent.getEmail());
	            student.setGender(newStudent.getGender());
	            student.setCourse(newStudent.getCourse());
	        }
		
		return studentRepo.save(student);
	}

	@Override
	public void delete(long id) {
		studentRepo.deleteById(id);
	}

}
