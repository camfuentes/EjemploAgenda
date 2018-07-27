package com.agenda.model;

import java.util.HashSet;
import java.util.Set;

import com.agenda.entity.UserRole;

public class UserModel {

	private String username;
	private String password;
	private boolean enabled = true;

	public UserModel() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserModel [username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

}
