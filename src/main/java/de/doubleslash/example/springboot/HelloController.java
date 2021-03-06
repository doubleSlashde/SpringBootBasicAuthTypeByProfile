package de.doubleslash.example.springboot;

import java.text.MessageFormat;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/protected")
	public String index() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails currentUser = (UserDetails) authentication.getPrincipal();

		return MessageFormat.format("Hello {0} [your role(s): {1}]!", currentUser.getUsername(),
				currentUser.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(", ")));
	}

	@RequestMapping("/anonymous")
	public String anonymous() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return MessageFormat.format("Hello {0}!", authentication.getPrincipal());
	}

}