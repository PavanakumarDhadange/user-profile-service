package com.synchrony.codingchallenege.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.codingchallenege.dto.UserDTO;
import com.synchrony.codingchallenege.dto.UserRegistrationDTO;
import com.synchrony.codingchallenege.service.UserRegistrationService;

@RestController
@RequestMapping("userprofile/api")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userService;
	
	@PostMapping("/v1/registeruser")
	public ResponseEntity<String> registerUser(UserRegistrationDTO userRegistrationDTO) {
		if(userService.registerUser(userRegistrationDTO)!=null) {
			return new ResponseEntity<String>("User Registartion Successful", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User Registartion Failed", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/v1/users/{userId}")
	public ResponseEntity<Object> fetchUserInfo(@PathVariable("userId") String userId) {
		UserDTO user = userService.getUserInfo(userId);
		if(user==null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
