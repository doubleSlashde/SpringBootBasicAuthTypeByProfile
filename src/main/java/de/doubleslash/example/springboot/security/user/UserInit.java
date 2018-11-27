package de.doubleslash.example.springboot.security.user;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import de.doubleslash.example.springboot.security.user.UserEntity.Role;

@Component
@Profile({ "dao", "jdbc" })
public class UserInit implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(UserInit.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public void run(final String... args) throws Exception {

		LOG.debug("Creating user...");
		
		final UserEntity user = new UserEntity("ben",
				// Passwort hashen
				new BCryptPasswordEncoder().encode("userFromDB"), //
				true, Role.ADMIN);
		userRepo.save(user);
		
		LOG.debug("Created the following user: {}", // 
				ReflectionToStringBuilder.toString(user, ToStringStyle.SHORT_PREFIX_STYLE));

	}

}
