package com.synchrony.codingchallenege.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_images")
public class ImageEntity {

	@Id
	private String imageId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "delete_hash")
	private String imageDeleteHash;
	
	@Column(name = "link")
	private String link;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImageDeleteHash() {
		return imageDeleteHash;
	}

	public void setImageDeleteHash(String imageDeleteHash) {
		this.imageDeleteHash = imageDeleteHash;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String string) {
		this.imageId = string;
	}

	public String getUser() {
		return userId;
	}

	public void setUser(String userEntity) {
		this.userId = userEntity;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}	
	
}
