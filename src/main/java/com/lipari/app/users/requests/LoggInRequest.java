package com.lipari.app.users.requests;

import org.springframework.stereotype.Component;

@Component
public class LoggInRequest {

	private String username,password;
	
	public LoggInRequest() {
		
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

}
