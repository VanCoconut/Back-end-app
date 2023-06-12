package com.lipari.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.lipari.app")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		
	}
}
