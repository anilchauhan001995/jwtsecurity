package com.security.jwtsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwtsecurity.model.AuthenticationRequest;
import com.security.jwtsecurity.model.AuthenticationResponse;
import com.security.jwtsecurity.model.UserRegisterRequest;
import com.security.jwtsecurity.service.CustomUserDetailService;
import com.security.jwtsecurity.service.UserService;
import com.security.jwtsecurity.util.Jwtutil;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Jwtutil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationrequest(@RequestBody AuthenticationRequest authRequest) throws Exception{
		System.out.println("authenticate controller");
		try {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userDetails = customUserDetailService.loadUserByUsername(authRequest.getUsername());
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
		if (userService.save(request) != null ) {
			return  ResponseEntity.ok("Success");
		}
		return null;
	}
}
