package com.dsafetech.cricket.service;

import com.dsafetech.cricket.entity.AdminRegistraion;


public interface AdminService {
	public AdminRegistraion saveadmin(AdminRegistraion adminRegistraion);
	 boolean isValidadmin(String username, String password);

}
