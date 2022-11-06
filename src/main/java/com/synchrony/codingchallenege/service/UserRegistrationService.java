package com.synchrony.codingchallenege.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synchrony.codingchallenege.dto.ImageDTO;
import com.synchrony.codingchallenege.dto.UserDTO;
import com.synchrony.codingchallenege.dto.UserRegistrationDTO;
import com.synchrony.codingchallenege.dto.ViewUserDTO;
import com.synchrony.codingchallenege.entity.UserEntity;
import com.synchrony.codingchallenege.repository.ImageRepository;
import com.synchrony.codingchallenege.repository.UserRepository;

@Service
public class UserRegistrationService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	public UserEntity registerUser(UserRegistrationDTO user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(user.getUserId());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setGender(user.getGender());
		userEntity.setPassword(user.getPassword());
		userEntity = userRepository.save(userEntity);
		return userEntity;
	}
	
	public UserDTO getUserInfo(String userId) {
		
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if(userEntity.isPresent()) {
			ViewUserDTO user = new ViewUserDTO();
			user.setUserId(userEntity.get().getUserId());
			user.setFirstName(userEntity.get().getFirstName());
			user.setLastName(userEntity.get().getLastName());
			user.setGender(userEntity.get().getGender());
			List<ImageDTO> userImages = new ArrayList<>();
			imageRepository.findAllByUserId(userId).forEach(image -> {
				ImageDTO userImage = new ImageDTO();
				userImage.setImageId(image.getImageId());
				userImage.setImageLink(image.getLink());
				userImages.add(userImage);
			});
			user.setUserImages(userImages);
			return user;
		}
		return null;
	}
}
