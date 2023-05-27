package com.abcBank.documentMangment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DocumentMangmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentMangmentApplication.class, args);
	}

}
