package com.dosreis.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dosreis.entity.Cliente;
import com.dosreis.repository.ClienteRepository;

@Configuration
@Profile("developement")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente(null, "Sarah");
		Cliente c2 = new Cliente(null, "Erick");
		
		clienteRepo.saveAll(Arrays.asList(c1, c2));
	}

}
