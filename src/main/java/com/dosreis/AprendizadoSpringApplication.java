package com.dosreis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AprendizadoSpringApplication {

	@Value("${application.name}")
	private String applicationName;
	
	
	
	@GetMapping("/ola")
	public String helloWorld() {
		return applicationName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AprendizadoSpringApplication.class, args);
	}

}