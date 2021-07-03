package com.security.jwtsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("/user")
	public String user() {
		return "User Home Page";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Admin Home Page";
	}

}
