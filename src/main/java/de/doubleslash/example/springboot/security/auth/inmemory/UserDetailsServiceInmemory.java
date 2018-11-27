package de.doubleslash.example.springboot.security.auth.inmemory;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class UserDetailsServiceInmemory implements UserDetailsService {
	
	@Value("${my.config.user.name:user}")
	private String username;

	@Value("${my.config.user.pw:hardcodedDefault}")
	private String pw;

	@Value("${my.config.user.roles:ADMIN}")
	private String[] roles;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final SimpleUser user = findUserbyUsername(username);

		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRoles());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}

	private SimpleUser findUserbyUsername(String username) {
		if (username.equalsIgnoreCase(username)) {
			return new SimpleUser(username, pw, roles);
		}
		return null;
	}
}