package de.doubleslash.example.springboot.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MySecurityConfigurer extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MySecurityConfigurer.class);

	/**
	 * H2 console (http://localhost:8080/h2-console) only in development profile.
	 * Logon data for H2 see application.properties.
	 */
	@Configuration
	@Order(1)
	@Profile("dev")
	public static class H2AuthConf extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.antMatcher("/h2-console/**") //
					.authorizeRequests().anyRequest().permitAll() //
					.and() //
					.csrf().disable();
			http.headers().frameOptions().disable();
		}

	}

	/**
	 * Configure the paths with basic auth.
	 */
	@Configuration
	@Order(2)
	public static class BasicAuthProtectionForApp extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.authorizeRequests() //
					.antMatchers("/protected").fullyAuthenticated() //
					.and() //
					.httpBasic();

		}

	}

	/**
	 * Configure the paths with anonymous access.
	 */
	@Configuration
	@Order(3)
	public static class Anonymous extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.authorizeRequests() //
					.antMatchers("/anonymous").anonymous();

		}

	}

}