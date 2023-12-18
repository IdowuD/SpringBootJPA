package com.hccs.project3;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.hccs.project3.controller.StudentController;

@SpringBootApplication
public class Project3Application {
	private static void extracted(ConfigurableApplicationContext context) throws IOException {

		StudentController studentController = context.getBean(StudentController.class);
		studentController.loadData();;
	}

	public static void main(String[] args) throws IOException{
		ConfigurableApplicationContext context = SpringApplication.run(Project3Application.class, args);
		extracted(context);
	}

}
