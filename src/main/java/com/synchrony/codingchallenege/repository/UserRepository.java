package com.synchrony.codingchallenege.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.codingchallenege.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String>{
	
}
