package com.dsafetech.cricket.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dsafetech.cricket.entity.UserRegister;

@Repository
public interface UserRepo extends MongoRepository<UserRegister, String> {
	UserRegister findByMobileNumber(long mobileNumber);
    UserRegister findByUsername(String username);
    UserRegister findByGmailId(String gmailId);
    UserRegister findByUsernameAndGmailId(String username, String gmailId);
}
