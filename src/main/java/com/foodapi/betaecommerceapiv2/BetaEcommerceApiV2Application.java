package com.foodapi.betaecommerceapiv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BetaEcommerceApiV2Application {

	public static void main(String[] args) {
		SpringApplication.run(BetaEcommerceApiV2Application.class, args);
		System.out.print("Server connected at localhost:8080 ...");
	}

}
