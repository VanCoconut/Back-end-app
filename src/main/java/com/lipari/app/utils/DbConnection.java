package com.lipari.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbConnection {

	
	private final DatabaseConfigBean configBean;

	@Autowired	
	public DbConnection(DatabaseConfigBean configBean) {

		this.configBean = configBean;
	}

	public Connection openConnection() throws SQLException {
		return DriverManager.getConnection(configBean.getDatabaseUrl(), configBean.getDatabaseUsername(),
				configBean.getDatabasePassword());

	}

}
