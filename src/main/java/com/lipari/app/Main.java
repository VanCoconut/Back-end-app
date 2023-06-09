package com.lipari.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Demo2Application;
import com.lipari.app.utils.DatabaseConfigBean;
import com.sun.tools.javac.Main;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
	
		SpringApplication.run(Main.class, args);
		
	}

}
