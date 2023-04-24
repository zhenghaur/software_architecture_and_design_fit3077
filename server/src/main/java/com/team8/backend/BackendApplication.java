package com.team8.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		System.setProperty("server.port", "9999");
		SpringApplication.run(BackendApplication.class, args);
	}

}
