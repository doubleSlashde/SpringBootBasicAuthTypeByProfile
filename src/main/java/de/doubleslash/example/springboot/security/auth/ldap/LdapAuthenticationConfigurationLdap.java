package de.doubleslash.example.springboot.security.auth.ldap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
@Profile("ldap")
public class LdapAuthenticationConfigurationLdap {

	private static final Logger LOG = LoggerFactory.getLogger(LdapAuthenticationConfigurationLdap.class);

	@Autowired
	private AuthenticationManagerBuilder auth;

	@PostConstruct
	public void configureGlobal() throws Exception {

		LOG.debug("Configuring ldap auth against embedded testserver...");

		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people") //
				.groupSearchBase("ou=groups") //
				.contextSource() //
				.url("ldap://localhost:8389/dc=springframework,dc=org") //
				.and() //
				.passwordCompare() //
				.passwordEncoder(new LdapShaPasswordEncoder()) //
				// FIXME why not working https://info.michael-simons.eu/2018/01/13/spring-security-5-new-password-storage-format/
				//.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
				.passwordAttribute("userPassword");

	}
}