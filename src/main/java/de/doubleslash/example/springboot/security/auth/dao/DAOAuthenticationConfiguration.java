package de.doubleslash.example.springboot.security.auth.dao;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("dao")
public class DAOAuthenticationConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(DAOAuthenticationConfiguration.class);
	
	@Autowired
	private AuthenticationManagerBuilder auth;
	
	@Autowired
	private UserDetailsServiceDAO userDetailsService;
	
	@PostConstruct
	public void configure() throws Exception {

		LOG.debug("Configuring authentication using a user details services which uses JPA to retrieve user information...");

		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}