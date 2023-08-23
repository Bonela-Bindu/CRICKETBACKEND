package com.dsafetech.cricket.serviceimp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsafetech.cricket.entity.AdminRegistraion;
import com.dsafetech.cricket.entity.UserRegister;
import com.dsafetech.cricket.exceptions.TeamAlreadyExsists;
import com.dsafetech.cricket.exceptions.UserNameAlreadyExists;
import com.dsafetech.cricket.repo.Adminrepo;
import com.dsafetech.cricket.service.AdminService;

@Service
public class AdminServiceImplement implements AdminService {
	@Autowired
	private Adminrepo adminrepo;
	private String generateResetToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public AdminRegistraion saveadmin(AdminRegistraion adminRegistraion) {
		String resetToken = generateResetToken();
		adminRegistraion.setResetToken(resetToken);
		AdminRegistraion existingadmin = adminrepo.findByPhone(adminRegistraion.getPhone());
		AdminRegistraion existingusername = adminrepo.findByusername(adminRegistraion.getUsername());
		if (existingadmin != null) {
			throw new TeamAlreadyExsists(
					"your phonenumber has Registered alredy with" + " " + "Name" + " " + adminRegistraion.getLastName());
		}
		if (existingusername != null) {
			throw new UserNameAlreadyExists(adminRegistraion.getUsername() + " "
					+ "this username is already taken by someone try with anotherone" + " " + "suggestiongs" + " "
					+ adminRegistraion.getLastName() + "@00" + " " + "or" + adminRegistraion.getLastName() + "123");
		}
		if (adminRegistraion.getPassword().length() < 6) {
			throw new IllegalArgumentException("Password must be at least 6 characters long");
		}
		if (!adminRegistraion.getPassword().matches(".*\\d.*")) {
			throw new IllegalArgumentException("Password must contain at least one digit");
		}

		return adminrepo.save(adminRegistraion);
	}
	@Override
    public boolean isValidadmin(String username, String password) {
		AdminRegistraion adminRegistraion = adminrepo.findByusername(username);
		return adminRegistraion != null && adminRegistraion.getPassword().equals(password);
	}
}
