package com.easydiet.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.easydiet")
@EnableJpaRepositories("com.easydiet")
@EntityScan(basePackages = "com.easydiet")
public class EasyDietApplication {
	public static void main(String[] args) {
		SpringApplication.run(EasyDietApplication.class, args);
	}
}
