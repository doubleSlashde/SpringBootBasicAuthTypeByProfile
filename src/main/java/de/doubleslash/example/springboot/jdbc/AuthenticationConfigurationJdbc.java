package de.doubleslash.example.springboot.jdbc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("jdbc")
public class AuthenticationConfigurationJdbc extends GlobalAuthenticationConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationConfigurationJdbc.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		
		LOG.debug("configuring jdbc auth");

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("select username, password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, role from user where username=?");

	}
}