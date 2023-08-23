package com.dsafetech.cricket.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dsafetech.cricket.entity.TeamEntity;

@Repository
public interface TeamRepo extends MongoRepository<TeamEntity, String>{
	
	
	TeamEntity findByteamName(String teamName);
	List<TeamEntity> findByDate(String  date);
	Optional<TeamEntity> findByContactnumber(long contactnumber);
	
}
