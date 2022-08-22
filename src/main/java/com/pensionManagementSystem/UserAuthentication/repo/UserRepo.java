package com.pensionManagementSystem.UserAuthentication.repo;


import org.springframework.data.repository.CrudRepository;

import com.pensionManagementSystem.UserAuthentication.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
}
