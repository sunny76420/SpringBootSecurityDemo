package com.example.demo.security;

import java.util.Set;
import com.google.common.collect.Sets;
import static com.example.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
	ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
	INSTRUCTOR(Sets.newHashSet(COURSE_READ, STUDENT_READ)),
	STUDENT(Sets.newHashSet());

	private final Set<ApplicationUserPermission> permissions;

	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

}
