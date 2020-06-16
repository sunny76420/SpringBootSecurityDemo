package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
//import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StudentService;
import com.example.demo.student.Student;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	
	private final StudentService studentService;
	
	@Autowired
	public StudentManagementController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
	public List<Student> getAllStudents(){
		return studentService.getAllStudent();
	}
	
	@GetMapping(path="{studentId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
	public Student getStudentById(@PathVariable("studentId") UUID studentId) {
		return studentService.getStudentById(studentId).orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exist"));
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@Valid @RequestBody Student student) {
		studentService.addStudent(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") UUID studentId) {
		studentService.deletePerson(studentId);
	}
	
	@PutMapping(path = "{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable("studentId") UUID studentId, @Valid @RequestBody Student student) {
		studentService.updateStudent(studentId, student);
	}

}
