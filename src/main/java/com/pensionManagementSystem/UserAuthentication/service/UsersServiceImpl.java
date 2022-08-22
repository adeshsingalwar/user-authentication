package com.pensionManagementSystem.UserAuthentication.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pensionManagementSystem.UserAuthentication.entity.UserEntity;
import com.pensionManagementSystem.UserAuthentication.repo.UserRepo;
import com.pensionManagementSystem.UserAuthentication.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {

	UserRepo repo;
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UsersServiceImpl(UserRepo repo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.repo = repo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDetail) {
		userDetail.setUserId(UUID.randomUUID().toString());
		userDetail.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetail.getPassword()));

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetail, UserEntity.class);

		repo.save(userEntity);
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = repo.findByEmail(username);

		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}
	
	@Override
	public UserDto getUserDetailsByEmail(String email) { 
		UserEntity userEntity = repo.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

}
