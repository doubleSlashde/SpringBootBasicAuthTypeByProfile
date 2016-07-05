package de.doubleslash.example.springboot.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
@Profile("default")
public class AuthenticationConfigurationInmemory extends GlobalAuthenticationConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationConfigurationInmemory.class);

	// siehe application.properties. Kann durch VM-Argument beim Start überschrieben werden.
	// Weitere Möglichkeiten siehe:
	// http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
	// Falls nichts konfiguriert ist kann mittels Doppelpunkt-Notation ein hartkodiertes Defaultpasswort angegeben
	// werden:
	@Value("${my.config.user.pw:hardcodedDefault}")
	private String pw;

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

		LOG.debug("configuring inmemory auth");

		auth.inMemoryAuthentication().withUser("user").password(pw).roles("USER");
	}
}