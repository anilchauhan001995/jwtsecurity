package com.security.jwtsecurity.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.jwtsecurity.entity.Role;
import com.security.jwtsecurity.entity.User;
import com.security.jwtsecurity.model.UserRegisterRequest;
import com.security.jwtsecurity.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User save(UserRegisterRequest userRequest) {
		User user = new User();
		//Set<Role> roles = new HashSet<>();
		
		user.setUsername(userRequest.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		user.setName(userRequest.getName());
		for (String role: userRequest.getRoles()) {
			Role r = new Role(role);
			user.getRoles().add(r);
			r.getUsers().add(user);
		}
		return userRepository.save(user);
	}
}
