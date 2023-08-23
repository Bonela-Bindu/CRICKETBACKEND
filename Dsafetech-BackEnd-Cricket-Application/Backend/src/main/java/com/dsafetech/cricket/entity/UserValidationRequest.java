package com.dsafetech.cricket.entity;

import lombok.Data;

@Data
public class UserValidationRequest {
    private String username;
    private String password;
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserValidationRequest [username=" + username + ", password=" + password + "]";
	}
	public UserValidationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public UserValidationRequest() {
		super();
	}
    
}