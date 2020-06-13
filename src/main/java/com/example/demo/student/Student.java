package com.example.demo.student;

import java.util.UUID;

//import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

	private final UUID studentId;
	private final String studentName;
	private final String className;
	private final boolean feePaid;
	private final String email;
	private final String phone;

	public Student(@JsonProperty("studentId") UUID studentId, @JsonProperty("studentName") String studentName,
			@JsonProperty("className") String className, @JsonProperty("feePaid") boolean feePaid,
			@JsonProperty("email") String email, @JsonProperty("phone") String phone) {
		this.phone = phone;
		this.feePaid = feePaid;
		this.className = className;
		this.email = email;
		this.studentId = studentId;
		this.studentName = studentName;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getClassName() {
		return className;
	}

	public boolean isFeePaid() {
		return feePaid;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

}
