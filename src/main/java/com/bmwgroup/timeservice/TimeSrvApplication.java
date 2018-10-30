package com.bmwgroup.timeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class TimeSrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeSrvApplication.class, args);
	}
}
