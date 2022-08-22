package com.pensionManagementSystem.UserAuthentication;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pensionManagementSystem.UserAuthentication.login.model.LoginRequestModel;
import com.pensionManagementSystem.UserAuthentication.service.UsersService;
import com.pensionManagementSystem.UserAuthentication.shared.UserDto;
import com.pensionManagementSystem.UserAuthentication.signup.SignupRequestModel;
import com.pensionManagementSystem.UserAuthentication.signup.UserSignup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//@SpringBootTest
@WebMvcTest(UserSignup.class)
class UserAuthenticationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	UsersService service;

	@Test
	public void testSignup() throws Exception {
		UserDto dto = new UserDto();
		dto.setUserId(null);
		dto.setEmail("lily@gmail.com");
		dto.setUsername("lily");
		dto.setPassword("123");
		dto.setEncryptedPassword(null);
		
		when(service.createUser(any(UserDto.class))).thenReturn(dto);
		
		SignupRequestModel signupModel = new SignupRequestModel("lily@gmail.com", "lily", "123");
		String signupJson = new ObjectMapper().writeValueAsString(signupModel);

		RequestBuilder request = MockMvcRequestBuilders.post("/user-auth/signup")
				.contentType(MediaType.APPLICATION_JSON).content(signupJson).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isCreated())
							.andExpect(jsonPath("$.email", is("lily@gmail.com")))
							.andExpect(jsonPath("$.username", is("lily")))
							.andReturn();


		int actualResultStatus = result.getResponse().getStatus();
		int expectedResult = HttpStatus.CREATED.value();
		
		assertEquals(expectedResult, actualResultStatus,"Result not as expected!");
	}

	@Test
	void testLogin() throws Exception {
		UserDto dto = new UserDto();
		dto.setUserId(null);
		dto.setEmail("lily@gmail.com");
		dto.setUsername("lily");
		dto.setPassword("123");
		dto.setEncryptedPassword(null);
		when(service.getUserDetailsByEmail(any(String.class))).thenReturn(dto);
		
		LoginRequestModel signupModel = new LoginRequestModel();
		signupModel.setEmail("lily@gmail.com");
		signupModel.setPassword("123");
		String signupJson = new ObjectMapper().writeValueAsString(signupModel);

		RequestBuilder request = MockMvcRequestBuilders.post("/user-auth/login")
				.contentType(MediaType.APPLICATION_JSON).content(signupJson).accept(MediaType.APPLICATION_JSON);
		
	}
}
