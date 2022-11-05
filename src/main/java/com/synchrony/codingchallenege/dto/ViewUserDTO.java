package com.synchrony.codingchallenege.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewUserDTO extends UserDTO {

	@JsonProperty("userImages")
	private List<ImageDTO> userImages;

	public List<ImageDTO> getUserImages() {
		return userImages;
	}

	public void setUserImages(List<ImageDTO> userImages) {
		this.userImages = userImages;
	}
	
}
