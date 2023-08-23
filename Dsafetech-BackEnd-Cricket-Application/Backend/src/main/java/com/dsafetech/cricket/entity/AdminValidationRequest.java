package com.dsafetech.cricket.entity;

public class AdminValidationRequest {
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
		public AdminValidationRequest(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}
		public AdminValidationRequest() {
			super();
		}
	    
}
