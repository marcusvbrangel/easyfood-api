package com.marvin.easyfoodapi;

import com.marvin.easyfoodapi.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class EasyFoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyFoodApiApplication.class, args);

	}

}
