package com.dosreis.configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dosreis.entity.Cliente;
import com.dosreis.entity.Pedido;
import com.dosreis.repository.ClienteRepository;
import com.dosreis.repository.PedidoRepository;

@Configuration
@Profile("developement")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente(null, "Sarah");
		Cliente c2 = new Cliente(null, "Erick");
		
		clienteRepo.saveAll(Arrays.asList(c1, c2));
		
		//List<Cliente> listC = clienteRepo.buscaPorNome("Er");
		
		Pedido p1 = new Pedido(null, c1, LocalDate.now(), new BigDecimal(100));
		Pedido p2 = new Pedido(null, c1, LocalDate.now(), new BigDecimal(99.80));
		pedidoRepo.saveAll(Arrays.asList(p1, p2));
		
		System.out.println(clienteRepo.findClienteFecthPedidos(1));
		
		System.out.println(pedidoRepo.findByIdCliente(c1));
	}

}
