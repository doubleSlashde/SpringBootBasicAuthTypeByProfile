package de.doubleslash.example.springboot.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
@Profile("default")
public class AuthenticationConfigurationInmemory extends GlobalAuthenticationConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationConfigurationInmemory.class);

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

		LOG.debug("configuring inmemory auth");

		auth.inMemoryAuthentication().withUser("user").password("inmemory").roles("USER");
	}
}