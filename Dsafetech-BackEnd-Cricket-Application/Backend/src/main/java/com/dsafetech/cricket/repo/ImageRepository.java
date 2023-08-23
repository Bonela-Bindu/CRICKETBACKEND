package com.dsafetech.cricket.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dsafetech.cricket.entity.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
	Image findBybrandContactNumber(long brandContactNumber);
}