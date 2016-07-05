package de.doubleslash.example.springboot.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("dao")
public class AuthenticationConfigurationDao extends GlobalAuthenticationConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationConfigurationDao.class);

	@Autowired
	private UserDetailsServiceDAO userDetailsService;
	
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

		LOG.debug("configuring DAO auth");

		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}