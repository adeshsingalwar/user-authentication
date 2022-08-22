package com.pensionManagementSystem.UserAuthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-auth")
public class UserAuthController {
	@GetMapping("/user")
	public String helloUser() {
		return "Hello User";
	}
}
