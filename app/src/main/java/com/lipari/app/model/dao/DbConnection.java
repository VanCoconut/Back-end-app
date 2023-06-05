package com.lipari.app.model.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lipari.app.services.ConfigBean;

public class DbConnection {

	ApplicationContext context = new ClassPathXmlApplicationContext("/app-context.xml");
	
	private static DbConnection _instance = null;
	
	private ConfigBean configBean = (ConfigBean) context.getBean("configBean");
	
	private DbConnection() {}
	
	public Connection initService() throws SQLException {
		return DriverManager.getConnection(configBean.getDatabaseUrl(),configBean.getDatabaseUsername(),configBean.getDatabasePassword());

	}

	public static DbConnection getInstance() throws SQLException, FileNotFoundException, IOException {
		if (_instance != null) {
			return _instance;
		} else {
			_instance = new DbConnection();

		}
		return _instance;
	}
}
