package com.pensionManagementSystem.UserAuthentication.shared;

import java.io.Serializable;

public class UserDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3250280168430575431L;
	private String userId;
	private String email;
	private String username;
	private String password;
	private String encryptedPassword;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
