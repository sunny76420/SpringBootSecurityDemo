package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentService;
import com.example.demo.student.Student;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<Student> getAllStudents(){
		return studentService.getAllStudent();
	}
	
	@GetMapping(path="{studentId}")
	public Student getStudentById(@PathVariable("studentId") UUID studentId) {
		return studentService.getStudentById(studentId).orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist"));
	}
	
	@PostMapping
	public void registerNewStudent(@Valid @RequestBody Student student) {
		studentService.addStudent(student);
	}

//	@GetMapping(path = "{studentId}")
//	public Student getStudent(@PathVariable("studentId") Integer studentId) {
//		return studentService.getAllStudent();
//		return STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId())).findFirst()
//				.orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist"));
//	}

}
