package com.dsafetech.cricket.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class AdminRegistraion {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String gmailId;
	private String gender;
	private long phone;
	private String username;
	private String password;
	private String resetToken;
	public String getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGmailId() {
		return gmailId;
	}
	public String getGender() {
		return gender;
	}
	public long getPhone() {
		return phone;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public AdminRegistraion(String id, String firstName, String lastName, String gmailId, String gender, long phone,
			String username, String password, String resetToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gmailId = gmailId;
		this.gender = gender;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.resetToken = resetToken;
	}
	public AdminRegistraion() {
		super();
	}
	@Override
	public String toString() {
		return "AdminRegistraion [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gmailId="
				+ gmailId + ", gender=" + gender + ", phone=" + phone + ", username=" + username + ", password="
				+ password + ", resetToken=" + resetToken + "]";
	}
	
}
