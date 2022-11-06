package com.synchrony.codingchallenege.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageUploadResponse {

	@JsonProperty("data")
	private ImageUploadData data;

	public ImageUploadData getData() {
		return data;
	}

	public void setData(ImageUploadData data) {
		this.data = data;
	}
	
}
