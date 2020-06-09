package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
		.antMatchers("/api/**").hasRole(STUDENT.name()) //Role based Authentication.
		.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), INSTRUCTOR.name())
		.anyRequest().authenticated().and().httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails admin = User.builder().username("ashish").password(passwordEncoder.encode("123456"))
//				.roles(ApplicationUserRole.ADMIN.name())
				.authorities(ADMIN.getGrantedAutorities())
				.build();
		
		UserDetails instructor = User.builder().username("savita").password(passwordEncoder.encode("123456"))
//				.roles(ApplicationUserRole.INSTRUCTOR.name())
				.authorities(INSTRUCTOR.getGrantedAutorities())
				.build();
		
		UserDetails student = User.builder().username("anna").password(passwordEncoder.encode("password"))
//				.roles(ApplicationUserRole.STUDENT.name())
				.authorities(STUDENT.getGrantedAutorities())
				.build();

		return new InMemoryUserDetailsManager(admin, student, instructor);
	}

}
