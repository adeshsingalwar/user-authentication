package com.pensionManagementSystem.UserAuthentication.signup;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pensionManagementSystem.UserAuthentication.entity.UserResponseModel;
import com.pensionManagementSystem.UserAuthentication.service.UsersService;
import com.pensionManagementSystem.UserAuthentication.shared.UserDto;

@RestController
@RequestMapping("/user-auth")
public class UserSignup {
	@Autowired
	UsersService service;
	
	
	@PostMapping("/signup")
	public ResponseEntity<UserResponseModel> createUser(@RequestBody SignupRequestModel user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		UserDto createdUser = service.createUser(userDto);
		UserResponseModel returnUser = modelMapper.map(createdUser, UserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnUser);
	}
}
