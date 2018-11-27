package de.doubleslash.example.springboot.security.auth.inmemory;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("default")
public class InmemoryAuthenticationConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(InmemoryAuthenticationConfiguration.class);

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public UserDetailsServiceInmemory userDetailsService;
	
	@Autowired
	private AuthenticationManagerBuilder auth;

	@PostConstruct
	public void configure() throws Exception {

		LOG.debug("Configuring authentication using a user details service which uses user details from config.");

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}