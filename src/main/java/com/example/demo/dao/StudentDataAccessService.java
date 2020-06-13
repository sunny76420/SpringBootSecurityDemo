package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.student.Student;

@Repository("studentDao")
public class StudentDataAccessService implements StudentDao {

	private static List<Student> studentList = new ArrayList<>();

	@Override
	public int addStudent(UUID studentId, Student student) {
		studentList.add(new Student(studentId, student.getStudentName(), student.getClassName(), student.isFeePaid(),
				student.getEmail(), student.getPhone()));
		return 1;
	}

	@Override
	public List<Student> getAllStudent() {
		return studentList;
	}

	@Override
	public Optional<Student> selectStudentById(UUID studentId) {
		return studentList.stream().filter(student -> student.getStudentId().equals(studentId)).findFirst();
	}

	@Override
	public int deleteStudentById(UUID studentId) {
		Optional<Student> studentOptional = selectStudentById(studentId);
		if (!studentOptional.isPresent()) {
			return 0;
		} else {
			studentList.remove(studentOptional.get());
			return 1;
		}
	}

	@Override
	public int updateStudentById(UUID studentId, Student studentToUpdate) {
		return selectStudentById(studentId).map(student -> {
			int indexOfStudentToUpdate = studentList.indexOf(student);
			if (indexOfStudentToUpdate >= 0) {
				studentList.set(indexOfStudentToUpdate, new Student(studentId, studentToUpdate.getStudentName(), studentToUpdate.getClassName(), studentToUpdate.isFeePaid(),
						studentToUpdate.getEmail(), studentToUpdate.getPhone()));
				return 1;
			}
			return 0;
		}).orElse(0);
	}

}
