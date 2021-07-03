package com.security.jwtsecurity.model;

import java.util.Arrays;

public class UserRegisterRequest {

	private String username;
	private String password;
	private String name;
	private String[] roles;
	public String getUsername() {
		return username;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserRegisterRequest [username=" + username + ", password=" + password + ", roles="
				+ Arrays.toString(roles) + "]";
	}

	
	
}
