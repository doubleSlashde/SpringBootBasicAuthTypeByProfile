package de.doubleslash.example.springboot.security.auth.dao;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.doubleslash.example.springboot.security.user.UserEntity;
import de.doubleslash.example.springboot.security.user.UserRepository;

@Service
public class UserDetailsServiceDAO implements UserDetailsService {

	protected final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceDAO.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		LOG.info("Loading user by username \"{}\".", username);

		Optional<UserEntity> optUser = userRepo.findById(username);

		if (!optUser.isPresent()) {
			throw new UsernameNotFoundException(username + " not found");
		}

		UserEntity user = optUser.get();

		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
		builder.password(user.getPassword());
		builder.roles(new String[] { user.getRole().name() });

		return builder.build();
	}

}