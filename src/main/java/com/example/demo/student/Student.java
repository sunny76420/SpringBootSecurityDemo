package com.example.demo.student;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

	private final UUID studentId;
	private final String studentName;

	public Student(@JsonProperty("studentId") UUID studentId, @JsonProperty("studentName") String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

}
