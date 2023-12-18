package com.hccs.project3.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hccs.project3.model.Course;
import com.hccs.project3.model.Student;
import com.hccs.project3.service.StudentServiceImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@RestController
public class StudentController {
	@Autowired
	private StudentServiceImpl studentService;
	
	@GetMapping("/student")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping("/student")
	public Student save(@RequestBody Student student) {
		return studentService.save(student);
	}
	
	@PutMapping("/student/{id}")
	public Student update(@RequestBody Student student, @PathVariable long id) {
		return studentService.update(student, id);
	}
	
	@DeleteMapping("/student/{id}")
	public void delete(@PathVariable long id) {
		studentService.delete(id);
	}
	
	public void loadData(){
		ArrayList<Student> students = new ArrayList<Student>();
		
		Client client = Client.create();
		WebResource webResource = client.resource("https://hccs-advancejava.s3.amazonaws.com/student_course.json");
		
		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		if(clientResponse.getStatus() != 200) {
			throw new RuntimeException(clientResponse.toString());
		}
		try {
			JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));
			@SuppressWarnings("unchecked")
			Iterator<Object> iterator = jsonArray.iterator();
			
			String fName;
			String gender;
			String email;
			String courseNumber;
			String grade;
			long creditHrs;
			
			while(iterator.hasNext()) {
				ArrayList<Course> courses = new ArrayList<Course>();
				JSONObject jsonObject = (JSONObject) iterator.next();
				fName = (String)jsonObject.get("first_name");
				gender = (String)jsonObject.get("gender");
				email = (String)jsonObject.get("email");
				
				Student student = new Student(fName, email, gender);
				JSONArray classes = (JSONArray) jsonObject.get("course");
				
				if(classes == null) {
					courses.add(new Course("", "", 0));
					courses.add(new Course("", "", 0));
					student.setCourse(courses);
					students.add(student);
					studentService.save(student);
				}else {
					JSONObject subject =  (JSONObject)classes.get(0);
					courseNumber = (String)subject.get("courseNo");
					grade = (String)subject.get("grade");
					creditHrs = (long)subject.get("creditHours");
					Course course = new Course(courseNumber, grade, creditHrs);
					courses.add(course);
					student.setCourse(courses);
					
					subject =  (JSONObject)classes.get(1);
					courseNumber = (String)subject.get("courseNo");
					grade = (String)subject.get("grade");
					creditHrs = (long)subject.get("creditHours");
					course = new Course(courseNumber, grade, creditHrs);
					courses.add(course);
					student.setCourse(courses);	
					students.add(student);
					studentService.save(student);
				}		
			}
		}catch(Exception ex){
		ex.printStackTrace();
		}
	}
}
