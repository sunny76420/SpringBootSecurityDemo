package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentDao;
import com.example.demo.student.Student;

@Service
public class StudentService {
	
	private final StudentDao studentDao;
	
	@Autowired
	public StudentService(@Qualifier("studentDao") StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public int addStudent(Student student) {
		return studentDao.addStudent(student);
		
	}
	
	public List<Student> getAllStudent(){
		return studentDao.getAllStudent() ;
	}
	
	public Optional<Student> getStudentById(UUID studentId){
		return studentDao.selectStudentById(studentId);
	}
	
	public int deletePerson(UUID studentId) {
		return studentDao.deleteStudentById(studentId);
	}
	
	public int updateStudent(UUID studentId, Student student) {
		return studentDao.updateStudentById(studentId, student);
	}

	

}
