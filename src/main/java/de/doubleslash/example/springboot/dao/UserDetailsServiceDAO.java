package de.doubleslash.example.springboot.dao;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.doubleslash.example.springboot.user.User;
import de.doubleslash.example.springboot.user.UserRepository;

@Service
public class UserDetailsServiceDAO implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		
		logger.info("loadUserByUsername username="+username);
		
		User user = userRepo.findOne(username);
		
		if(user == null){
			throw new UsernameNotFoundException(username + " not found");
		}
		
		return new UserDetails() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {
				return user.isEnabled();
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return user.isEnabled();
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return user.isEnabled();
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return user.isEnabled();
			}
			
			@Override
			public String getUsername() {
				return username;
			}
			
			@Override
			public String getPassword() {
				// TODO how to use bcrypt here
				return user.getPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
				auths.add(new SimpleGrantedAuthority(user.getRole().name()));
				return auths;
			}
		};
	}

}