package com.slj.pg;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PgClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgClientApplication.class, args);
	}

}

