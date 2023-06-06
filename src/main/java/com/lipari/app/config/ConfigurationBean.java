package com.lipari.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.lipari.app.utils.ConfigBean;

@Configuration

@ComponentScan("com.lipari.app")

@PropertySources(
		{
				@PropertySource("/database.properties")
		})
public class ConfigurationBean {

	@Value("${database.url}")
	private String databaseUrl;
	@Value("${database.username}")
	private String databaseUsername;
	@Value("${database.password}")
	private String databasePassword;

	@Bean(name = "datasource")
	public ConfigBean getDatabaseConfigurationBean() {
		ConfigBean datasource = new ConfigBean();		
		datasource.setDatabasePassword(databasePassword);
		datasource.setDatabaseUsername(databaseUsername);
		datasource.setDatabaseUrl(databaseUrl);
		return datasource;
	}
}
