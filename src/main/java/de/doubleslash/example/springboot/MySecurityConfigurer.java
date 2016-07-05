package de.doubleslash.example.springboot;

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

	private static final Logger LOG = LoggerFactory.getLogger(MySecurityConfigurer.class);

	@Configuration
	@Order(1)
	// H2-Konsole nur in Profil "dev". Diese ist unter http://localhost:8080/h2-console verfügbar. 
	// Login-Daten für H2 siehe application.properties. 
	@Profile("dev")
	public static class H2AuthConf extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(final HttpSecurity http) throws Exception {

			// Für H2-Konsole keine Authentifizierung. Diese ist nur in Profil "dev" aktiv, siehe oben...
			http.antMatcher("/h2-console/**").authorizeRequests().anyRequest().permitAll().and().csrf().disable();
			http.headers().frameOptions().disable();
		}
		
	}

	@Configuration
	@Order(2)
	public static class AnonymousAccessPathConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			// alle ohne Auth erlaubt unter diesem Pfad
			http.antMatcher("/anonymous/**").authorizeRequests().anyRequest().permitAll().and().csrf().disable();
			http.headers().frameOptions().disable();
		}

	}
	
	@Configuration
	@Order(3)	
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