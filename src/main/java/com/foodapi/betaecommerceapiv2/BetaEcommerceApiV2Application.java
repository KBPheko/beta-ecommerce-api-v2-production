package com.foodapi.betaecommerceapiv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class BetaEcommerceApiV2Application {

	@Profile("prod")
	public static void main(String[] args) {
		SpringApplication.run(BetaEcommerceApiV2Application.class, args);
		System.out.print("Server connected at localhost:8080 ...");
	}

}
