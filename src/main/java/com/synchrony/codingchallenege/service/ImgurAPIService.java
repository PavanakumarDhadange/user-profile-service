package com.synchrony.codingchallenege.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.synchrony.codingchallenege.dto.ImageDTO;
import com.synchrony.codingchallenege.dto.ImageResposeDTO;
import com.synchrony.codingchallenege.dto.ImageUploadResponse;

@Service
public class ImgurAPIService {

	@Value("${app.imgur.clientid}")
	private String clientId;
	
	@Value("${app.imgur.url}")
	private String serverUrl;
	
	private RestTemplate apiService = new RestTemplate();
	
	public ResponseEntity<ImageUploadResponse> uploadImage(byte[] image) {
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", image);
        
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, getImageUploadHeaders());
        ResponseEntity<ImageUploadResponse> res = apiService.postForEntity(URI.create(serverUrl), requestEntity, ImageUploadResponse.class);
        return res;
	}
	
	public ResponseEntity<ImageResposeDTO> getImage(String imageId) {
		HttpEntity requestEntity = new HttpEntity(null, getHeaderForJsonData());
		return apiService.exchange(serverUrl+"/"+imageId, HttpMethod.GET, requestEntity, ImageResposeDTO.class);
	}

	public ResponseEntity<Object> deleteImage(String imageDeleteHash) {
		HttpEntity requestEntity = new HttpEntity(null, getHeaderForJsonData());
		return apiService.exchange(serverUrl+"/"+imageDeleteHash, HttpMethod.DELETE, requestEntity, Object.class);
	}
	
	private HttpHeaders getImageUploadHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", clientId);
		return headers;
	}
	
	private HttpHeaders getHeaderForJsonData() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", clientId);
		return headers;
	}
}
