package com.dsafetech.cricket.serviceimp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsafetech.cricket.entity.UserRegister;
import com.dsafetech.cricket.exceptions.TeamAlreadyExsists;
import com.dsafetech.cricket.exceptions.UserNameAlreadyExists;
import com.dsafetech.cricket.repo.UserRepo;
import com.dsafetech.cricket.service.Userservice;

@Service
public class UserServiceImplement implements Userservice {

	@Autowired
	private UserRepo userRepo;

	private String generateResetToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public UserRegister save1(UserRegister userRegister) {
		String resetToken = generateResetToken();
		userRegister.setResetToken(resetToken);
		UserRegister existingUser = userRepo.findByMobileNumber(userRegister.getMobileNumber());
		UserRegister existingusername = userRepo.findByUsername(userRegister.getUsername());
		if (existingUser != null) {
			throw new TeamAlreadyExsists(
					"your phonenumber has Registered alredy with" + " " + "Name" + " " + userRegister.getLastName());
		}
		if (existingusername != null) {
			throw new UserNameAlreadyExists(userRegister.getUsername() + " "
					+ "this username is already taken by someone try with anotherone" + " " + "suggestiongs" + " "
					+ userRegister.getLastName() + "@00" + " " + "or" + userRegister.getLastName() + "123");
		}
		if (userRegister.getPassword().length() < 6) {
			throw new IllegalArgumentException("Password must be at least 6 characters long");
		}
		if (!userRegister.getPassword().matches(".*\\d.*")) {
			throw new IllegalArgumentException("Password must contain at least one digit");
		}

		return userRepo.save(userRegister);
	}

	@Override
	public boolean isValidUser(String username, String password) {
		UserRegister userRegister = userRepo.findByUsername(username);
		return userRegister != null && userRegister.getPassword().equals(password);
	}

	@Override
	public List<UserRegister> getList() {
		return userRepo.findAll();
	}

}
