package com.example.orderservice;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan("com.example.orderservice.model")
@EnableJpaAuditing
@EnableFeignClients
public class OrderserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}
}
