package com.synchrony.codingchallenege.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.codingchallenege.dto.ImageDTO;

public interface ImageService {

	public ResponseEntity<String> uploadImage(String userId, MultipartFile imageFile) throws IOException;
	public List<ImageDTO> getImage(String userId);
	public ResponseEntity<Object> deleteImage(String imageId);
	
}
