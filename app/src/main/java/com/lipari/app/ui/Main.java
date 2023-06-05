package com.lipari.app.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lipari.app.controller.AdminController;
import com.lipari.app.controller.OrderController;
import com.lipari.app.model.dao.OrderDao;
import com.lipari.app.model.vo.Order;
import com.lipari.app.model.vo.User;
import com.lipari.app.services.ConfigBean;


public class Main {
	
	@Autowired(required=true)
	@Qualifier("configBean")
	private static ConfigBean configBean;

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
