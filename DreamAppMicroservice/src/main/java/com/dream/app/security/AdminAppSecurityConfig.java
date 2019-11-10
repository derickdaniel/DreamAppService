/*package com.dream.app.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@Order(2)
public class AdminAppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.csrf().disable().cors().and()
		.authorizeRequests()
		.antMatchers("/register").permitAll()
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.anyRequest().authenticated()
        .and()
        .httpBasic();
	}

	@Autowired
	public void configAuthenticated(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(dataSource);
		auth.jdbcAuthentication().dataSource(dataSource)
				.authoritiesByUsernameQuery("select email, role FROM app_user where email=?")
				.usersByUsernameQuery("select email, password, 1 FROM app_user where email=?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}*/
