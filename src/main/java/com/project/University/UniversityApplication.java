package com.project.University;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

}
