package com.synchrony.codingchallenege.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.synchrony.codingchallenege.dto.UserDTO;
import com.synchrony.codingchallenege.repository.ImageRepository;
import com.synchrony.codingchallenege.repository.UserRepository;
import com.synchrony.codingchallenege.service.UserRegistrationService;

import static org.mockito.Mockito.*;

import java.net.URI;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRegistrationController.class)
public class UserRegistrationControllerTest {

	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	private UserRegistrationService userService;
	
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	ImageRepository imageRepository;
	
	@Test
	public void fetchUserInfoTest() throws Exception {
		UserDTO user = new UserDTO();
		user.setUserId("XYZ");
		when(userService.getUserInfo(anyString())).thenReturn(user);
		
		mvc.perform(MockMvcRequestBuilders
				.get(URI.create("/userprofile/api/v1/users/XYZ"))
				.accept(MediaType.APPLICATION_JSON_VALUE)
				)
		.andExpect(status().isOk());
	}
}
