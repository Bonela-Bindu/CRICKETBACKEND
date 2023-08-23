package com.dsafetech.cricket.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;

@Entity
public class UserRegister {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String gmailId;
	private String gender;
	private String username;
	private String password;
	private long mobileNumber;
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
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public long getMobileNumber() {
		return mobileNumber;
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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	public UserRegister(String id, String firstName, String lastName, String gmailId, String gender, String username,
			String password, long mobileNumber, String resetToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gmailId = gmailId;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.resetToken = resetToken;
	}
	public UserRegister() {
		super();
	}
	@Override
	public String toString() {
		return "UserRegister [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gmailId=" + gmailId
				+ ", gender=" + gender + ", username=" + username + ", password=" + password + ", mobileNumber="
				+ mobileNumber + ", resetToken=" + resetToken + "]";
	}

}
