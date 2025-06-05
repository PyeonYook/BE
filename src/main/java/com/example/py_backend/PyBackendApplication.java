package com.example.py_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PyBackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PyBackendApplication.class, args);
	}

}
