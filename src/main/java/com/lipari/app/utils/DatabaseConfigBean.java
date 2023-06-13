package com.lipari.app.utils;


public class DatabaseConfigBean {
	
	private String databaseUrl;

	private String databaseUsername;
	
	private String databasePassword;

	public DatabaseConfigBean() {
	}
	
	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}
}
