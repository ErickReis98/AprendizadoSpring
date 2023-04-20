package com.dosreis.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.dosreis.entity.Cliente;
import com.dosreis.repository.ClienteRepository;

@ConfigProduction
public class webConfig {
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Bean
	public CommandLineRunner executa() {
		return args ->{

			
			
			System.out.println("Teste Profile");
			Cliente c1 = new Cliente(null, "Sarah");
			Cliente c2 = new Cliente(null, "Erick");
			
			clienteRepo.saveAll(Arrays.asList(c1, c2));
		};
	}
}
