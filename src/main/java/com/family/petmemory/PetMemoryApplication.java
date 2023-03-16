package com.family.petmemory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PetMemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetMemoryApplication.class, args);
	}

}
