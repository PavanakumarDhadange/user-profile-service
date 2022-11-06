package com.synchrony.codingchallenege.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.codingchallenege.dto.ImageDTO;
import com.synchrony.codingchallenege.dto.ImageResposeDTO;
import com.synchrony.codingchallenege.dto.ImageUploadData;
import com.synchrony.codingchallenege.dto.ImageUploadResponse;
import com.synchrony.codingchallenege.entity.ImageEntity;
import com.synchrony.codingchallenege.repository.ImageRepository;

@Service(value = "imgurimageservice")
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ImgurAPIService imgurService;
	
	@Autowired
	private EventPublisherService eventPublisher;
	
	@Override
	public ResponseEntity<String> uploadImage(String userId, MultipartFile imageFile) throws IOException {
		
		ResponseEntity<ImageUploadResponse> imrur = imgurService.uploadImage(imageFile.getBytes());
		if(imrur.getStatusCode()==HttpStatus.OK && imrur.hasBody()) {
			ImageUploadData response = imrur.getBody().getData();
			ImageEntity imageEntity = new ImageEntity(); 
			imageEntity.setImageId(response.getImageId());
			imageEntity.setImageName(imageFile.getOriginalFilename());
			imageEntity.setImageDeleteHash(response.getDeleteHash());
			imageEntity.setUser(userId);
			imageEntity.setLink(response.getImageLink());
			imageRepository.save(imageEntity);
			eventPublisher.publishEvent(userId, imageFile.getOriginalFilename());
			return new ResponseEntity<String>("Upload Successful", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Upload Failed", HttpStatus.EXPECTATION_FAILED);
	}

	@Override
	public List<ImageDTO> getImage(String userId) {
		
		final List<ImageDTO> images = new ArrayList<>();
		List<ImageEntity> imagesEntity = imageRepository.findAllByUserId(userId);
		imagesEntity.forEach(image -> {
			ResponseEntity<ImageResposeDTO> imageFromImgur = imgurService.getImage(image.getImageId());
			if(imageFromImgur.getStatusCode()==HttpStatus.OK && imageFromImgur.hasBody()) {
				images.add(imageFromImgur.getBody().getData());
			}
		});
		return images;
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteImage(String imageDeleteHash) {
		ResponseEntity<Object> response = imgurService.deleteImage(imageDeleteHash);
		if(response.getStatusCode()==HttpStatus.OK) {
			imageRepository.deleteByImageDeleteHash(imageDeleteHash);
		}
		return response;
	}

	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Client-ID dbd165bb412a21f");
		return headers;
	}
	
}
