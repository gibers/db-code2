package com.movedigital.dbcodeinterview2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.movedigital"})
public class DbCodeInterview2Application {

	public static void main(String[] args) {
		SpringApplication.run(DbCodeInterview2Application.class, args);
	}

}

