package com.dhdigital.loan.api.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource({"classpath:spring/camel-context.xml"})
public class LoanApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApIsApplication.class, args);
	}

}
