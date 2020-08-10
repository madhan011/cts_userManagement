package com.learningManagement.UserMangement.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class LoginRequest {

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String userName;
	
	private String password;
}
