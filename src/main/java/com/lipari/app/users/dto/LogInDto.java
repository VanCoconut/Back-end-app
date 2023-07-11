package com.lipari.app.users.dto;

import org.springframework.stereotype.Component;

/**
 * The type Log in dto not used.
 */
@Component
public class LogInDto {

    /**
     * The Username.
     */
    private String username, /**
     * The Password.
     */
    password;

    /**
     * Instantiates a new Log in dto.
     */
    public LogInDto() {
		
	}

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
		return username;
	}

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
		this.username = username;
	}

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
		return password;
	}

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
		this.password = password;
	}

}
