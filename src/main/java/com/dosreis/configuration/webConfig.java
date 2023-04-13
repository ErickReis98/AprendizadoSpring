package com.dosreis.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

//@ConfigProduction
public class webConfig {
	
	@Bean
	public CommandLineRunner executa() {
		return args ->{
			System.out.println("Teste Profile");
		};
	}
}
