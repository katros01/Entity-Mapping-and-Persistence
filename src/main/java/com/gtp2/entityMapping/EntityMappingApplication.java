package com.gtp2.entityMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EntityMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityMappingApplication.class, args);
	}

}
