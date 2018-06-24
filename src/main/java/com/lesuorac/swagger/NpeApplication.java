package com.lesuorac.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class NpeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NpeApplication.class, args);
	}

}
