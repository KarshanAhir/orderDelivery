package com.orderdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = { "com.orderdelivery", "com.orderdelivery.api", "com.orderdelivery.api.impl", "com.orderdelivery.entity",
		"com.orderdelivery.repository", "com.orderdelivery.service", "com.orderdelivery.service.impl", "com.orderdelivery.util" })
@Configuration
public class OrderdeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderdeliveryApplication.class, args);
	}

}
