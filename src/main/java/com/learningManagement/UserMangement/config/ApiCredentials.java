package com.learningManagement.UserMangement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiCredentials {

	private String resourUrl;
	
	private String user;
	
	private String pass;

	public String getResourUrl() {
		return resourUrl;
	}

	public void setResourUrl(String resourUrl) {
		this.resourUrl = resourUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
