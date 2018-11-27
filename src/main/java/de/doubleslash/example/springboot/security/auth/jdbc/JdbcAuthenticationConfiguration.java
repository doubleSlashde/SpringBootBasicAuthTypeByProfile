package de.doubleslash.example.springboot.security.auth.jdbc;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("jdbc")
public class JdbcAuthenticationConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(JdbcAuthenticationConfiguration.class);

	@Autowired
	private AuthenticationManagerBuilder auth;

	@Autowired
	DataSource dataSource;

	@PostConstruct
	public void configure() throws Exception {

		LOG.debug("Configuring authentication using JDBC for retrieving user information...");

		auth.jdbcAuthentication().dataSource(dataSource) //
				.passwordEncoder(new BCryptPasswordEncoder()) //
				.usersByUsernameQuery("select username, password, enabled from user where username=?")
				// FIXME why is role printed as "your role(s): 1" in the rest api result instead of role name?
				.authoritiesByUsernameQuery("select username, role from user where username=?");

	}
}