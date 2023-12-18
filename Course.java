package com.hccs.project3.model;

public class Course {
	private String courseNo;
	private String grade;
	private long creditHours;
	
	public Course() {}
	
	public Course(String courseNo, String grade, long creditHours) {
		this.courseNo = courseNo;
		this.grade = grade;
		this.creditHours = creditHours;
	}
	
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public long getCreditHours() {
		return creditHours;
	}
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	@Override
	public String toString() {
		return this.courseNo + " " + this.creditHours + " " + this.grade;
	}
}
