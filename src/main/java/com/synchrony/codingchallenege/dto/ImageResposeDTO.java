package com.synchrony.codingchallenege.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageResposeDTO {

	@JsonProperty("data")
	private ImageDTO data;

	public ImageDTO getData() {
		return data;
	}

	public void setData(ImageDTO data) {
		this.data = data;
	}
	
	
}
