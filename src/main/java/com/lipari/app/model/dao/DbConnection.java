package com.lipari.app.model.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lipari.app.config.ConfigurationBean;
import com.lipari.app.ui.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lipari.app.utils.ConfigBean;

public class DbConnection {

	
	//ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationBean.class);
	private static DbConnection _instance = null;
	
	//private ConfigBean configBean = context.getBean(ConfigBean.class);
	
	@Autowired
	private ConfigBean configBean;
	
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
