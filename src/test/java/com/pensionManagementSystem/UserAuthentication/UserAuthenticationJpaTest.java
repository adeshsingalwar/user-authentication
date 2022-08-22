package com.pensionManagementSystem.UserAuthentication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pensionManagementSystem.UserAuthentication.entity.UserEntity;
import com.pensionManagementSystem.UserAuthentication.repo.UserRepo;

@DataJpaTest
public class UserAuthenticationJpaTest {
	@Autowired
	UserRepo repo;
	
	

	@Test
	void userEntityTest() {
		UserEntity user = repo.findByEmail("lily@gmail.com");
		assertNotNull(user);
	}
}
