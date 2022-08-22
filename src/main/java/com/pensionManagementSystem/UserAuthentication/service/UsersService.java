package com.pensionManagementSystem.UserAuthentication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pensionManagementSystem.UserAuthentication.shared.UserDto;

public interface UsersService extends UserDetailsService {
	UserDto createUser(UserDto userDetail);
	UserDto getUserDetailsByEmail(String email);
}
