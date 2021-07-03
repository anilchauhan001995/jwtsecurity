package com.security.jwtsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
		user.setUsername(userRequest.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		user.setRole(userRequest.getRole());
		return userRepository.save(user);
	}
}
