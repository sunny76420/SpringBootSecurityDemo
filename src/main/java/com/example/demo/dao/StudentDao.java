package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.student.Student;

public interface StudentDao {
	
	int addStudent(UUID studentId, Student student);
	
	default int addStudent(Student student) {
		UUID studentId= UUID.randomUUID();
		return addStudent(studentId, student);
	}
	
	List<Student> getAllStudent();
	
	Optional<Student> selectStudentById(UUID studentId);
	
	int deleteStudentById(UUID studentId);
	
	int updateStudentById(UUID studentId, Student student);

}
