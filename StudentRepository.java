package com.hccs.project3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hccs.project3.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
