package com.dsafetech.cricket.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class UserResetPasswordRequest {

	String newPassword;
	String username;
	String gmailId;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGmailId() {
		return gmailId;
	}
	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}
	public UserResetPasswordRequest(String newPassword, String username, String gmailId) {
		super();
		this.newPassword = newPassword;
		this.username = username;
		this.gmailId = gmailId;
	}
	@Override
	public String toString() {
		return "UserResetPasswordRequest [newPassword=" + newPassword + ", username=" + username + ", gmailId="
				+ gmailId + "]";
	}
	public UserResetPasswordRequest() {
		super();
	}
	
}
