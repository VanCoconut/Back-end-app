package com.lipari.app.services;

public class ConfigBean {
	
	private String databaseUrl;
	private String databaseUsername;
	private String databasePassword;
	

	public ConfigBean(String databaseUrl,String databaseUsername, String databasePassword) {
		this.databaseUrl = databaseUrl;
		this.databaseUsername = databaseUsername;
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
