package com.dosreis.configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dosreis.entity.Cliente;
import com.dosreis.entity.Pedido;
import com.dosreis.entity.Produto;
import com.dosreis.repository.ClienteRepository;
import com.dosreis.repository.PedidoRepository;
import com.dosreis.repository.ProdutoRepository;

@Configuration
@Profile("developement")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente(null, "Sarah", "47324239811");
		Cliente c2 = new Cliente(null, "Erick", "46090833886");
		
		clienteRepo.saveAll(Arrays.asList(c1, c2));
	
		
		Pedido p1 = new Pedido(null, c1, LocalDate.now(), new BigDecimal(100));
		Pedido p2 = new Pedido(null, c1, LocalDate.now(), new BigDecimal(99.80));
		pedidoRepo.saveAll(Arrays.asList(p1, p2));
		
		Produto pr1 = new Produto(null, "Amendoas", new BigDecimal(12.45));
		Produto pr2 = new Produto(null, "Balões", new BigDecimal(19.90));
		produtoRepo.saveAll(Arrays.asList(pr1, pr2));
	}

}
