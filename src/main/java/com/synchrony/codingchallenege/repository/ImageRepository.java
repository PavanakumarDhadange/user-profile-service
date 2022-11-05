package com.synchrony.codingchallenege.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.codingchallenege.entity.ImageEntity;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Long> {

	List<ImageEntity> findAllByUserId(String userId);
	
	void deleteByImageDeleteHash(String imageDeleteHash);
	
}
