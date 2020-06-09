package com.example.demo.security;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
	
	public Set<SimpleGrantedAuthority> getGrantedAutorities(){
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
		.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		
		return permissions;
	}

}
