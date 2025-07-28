package com.ms.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Cards microservices REST API Documentation", description = " Cards microservices REST API Documentation", version = "v1", contact = @Contact(name = "klinton", email = "klintonece@gmail.com"), license = @License(name = "Apache 2.0")))
public class CardServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardServiceAppApplication.class, args);
	}

}
