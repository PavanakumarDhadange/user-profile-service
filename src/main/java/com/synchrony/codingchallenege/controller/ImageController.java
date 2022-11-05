package com.synchrony.codingchallenege.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.codingchallenege.dto.ImageDTO;
import com.synchrony.codingchallenege.service.ImageService;

@RestController
@RequestMapping("userimages/api")
public class ImageController {

	@Autowired
	@Qualifier("imgurimageservice")
	private ImageService imageService;
	
	@GetMapping(value="/v1/getuserimages/{userId}")
	public ResponseEntity<List<ImageDTO>> getUserImages(@PathVariable("userId") String userId) {
		List<ImageDTO> images = new ArrayList<>();
		if(StringUtils.hasText(userId)) {
			//imageService.getImage(imageId);
			images.addAll(imageService.getImage(userId));
		}
		return new ResponseEntity<>(images, HttpStatus.OK);
	}
	
	@PostMapping("/v1/uploadimage/{userId}")
	public ResponseEntity<String> uploadImage(@PathVariable("userId") String userId, @RequestParam("image") MultipartFile imageFile) throws IOException {
		return imageService.uploadImage(userId, imageFile);
	}
	
	@DeleteMapping("/v1/deleteimage/{imageDeleteHash}")
	public ResponseEntity<Object> deleteImage(@PathVariable("imageDeleteHash") String imageDeleteHash) {
		return imageService.deleteImage(imageDeleteHash);
	}
	
}
