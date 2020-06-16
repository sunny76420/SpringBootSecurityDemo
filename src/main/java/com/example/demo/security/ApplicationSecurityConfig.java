package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests()
		.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
		.antMatchers("/api/**").hasRole(STUDENT.name()) //Role based Authentication.
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails admin = User.builder().username("ashish").password(passwordEncoder.encode("123456"))
				.authorities(ADMIN.getGrantedAutorities())
				.build();
		
		UserDetails instructor = User.builder().username("savita").password(passwordEncoder.encode("123456"))
				.authorities(INSTRUCTOR.getGrantedAutorities())
				.build();
		
		UserDetails student = User.builder().username("anna").password(passwordEncoder.encode("password"))
				.authorities(STUDENT.getGrantedAutorities())
				.build();

		return new InMemoryUserDetailsManager(admin, student, instructor);
	}

}
