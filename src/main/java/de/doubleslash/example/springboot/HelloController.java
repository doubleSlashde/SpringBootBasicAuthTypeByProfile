package de.doubleslash.example.springboot;

import java.text.MessageFormat;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDetails currentUser = (UserDetails) authentication.getPrincipal();
		return MessageFormat.format("Hello {0}!", currentUser.getUsername());
	}

}