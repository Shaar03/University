package com.project.University;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableCaching
public class UniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate){
		return args -> {
			for(int i = 0; i < 100; i++)
				kafkaTemplate.send("university", "hello uni " + i);
		};
	}
}
