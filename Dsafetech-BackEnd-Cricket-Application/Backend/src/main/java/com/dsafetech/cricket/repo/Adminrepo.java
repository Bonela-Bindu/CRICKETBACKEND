package com.dsafetech.cricket.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dsafetech.cricket.entity.AdminRegistraion;

@Repository
public interface Adminrepo extends MongoRepository<AdminRegistraion, String> {
    AdminRegistraion findByPhone(long phone);
    AdminRegistraion findByusername(String username);
    AdminRegistraion findByUsernameAndPassword(String username, String password);
}