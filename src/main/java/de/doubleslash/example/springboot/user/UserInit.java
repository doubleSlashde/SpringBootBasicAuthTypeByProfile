package de.doubleslash.example.springboot.user;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import de.doubleslash.example.springboot.user.User.Role;

@Component
@Profile({ "dao", "jdbc" })
public class UserInit implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(UserInit.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public void run(final String... args) throws Exception {

		LOG.debug("creating user");
		
		final User user = new User("ben",
				// Passwort hashen
				new BCryptPasswordEncoder().encode("userFromDB"), //
				true, Role.ADMIN);
		userRepo.save(user);
		
		LOG.debug("created user " + ReflectionToStringBuilder.toString(user, ToStringStyle.SHORT_PREFIX_STYLE));

	}

}
