package com.lipari.app.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.lipari.app.utils.DatabaseConfigBean;


public class Main {
	
	@Autowired(required=true)
	@Qualifier("configBean")
	private static DatabaseConfigBean configBean;

	public static void main(String[] args) {
		/*
		 * ApplicationContext context = new
		 * ClassPathXmlApplicationContext("/app-context.xml"); ConfigBean c =
		 * (ConfigBean) context.getBean("configBean");
		 * System.out.println(c.getDatabaseUsername().endsWith(" "));
		 * System.out.println(c.getDatabasePassword().endsWith(" "));
		 */
		System.out.println(configBean.getDatabasePassword());
		
	}

}
