package de.doubleslash.example.springboot.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

	public enum Role {
		USER, ADMIN
	}

	@Id
	private String username;

	@Column
	private String password;

	@NotNull
	private boolean enabled = true;

	@NotNull
	private Role role;

	public User() {
	}

	public User(final String username, final String password, final boolean enabled, final Role role) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

}