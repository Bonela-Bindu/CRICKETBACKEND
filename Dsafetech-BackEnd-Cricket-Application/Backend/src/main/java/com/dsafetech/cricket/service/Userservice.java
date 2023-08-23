package com.dsafetech.cricket.service;


import java.util.List;

import com.dsafetech.cricket.entity.UserRegister;

public interface Userservice {
	public UserRegister save1(UserRegister userRegister);
	  boolean isValidUser(String username, String password);
	public List<UserRegister> getList();
}
