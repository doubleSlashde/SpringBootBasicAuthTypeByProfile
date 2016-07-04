package de.doubleslash.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MySecurityConfigurer extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(MySecurityConfigurer.class);


	@Configuration
	@Order(2)
	public static class BasicAuthProtectionForApp extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.authorizeRequests() //
					.anyRequest().authenticated() //
					.and() //
					.httpBasic();
		}

	}
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// here we only configured basic auth. 
	// please also see packages ldap and jdbc for configuration of credential source. 
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
}