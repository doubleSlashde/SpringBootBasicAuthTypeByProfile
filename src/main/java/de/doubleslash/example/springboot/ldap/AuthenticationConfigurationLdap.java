package de.doubleslash.example.springboot.ldap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
@Profile("ldap")
public class AuthenticationConfigurationLdap extends GlobalAuthenticationConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationConfigurationLdap.class);

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

		LOG.debug("configuring ldap auth against embedded testserver");
		
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups").contextSource()
				.ldif("classpath:test-server.ldif");

	}
}