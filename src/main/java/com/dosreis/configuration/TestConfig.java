package com.dosreis.configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dosreis.domain.entity.Cliente;
import com.dosreis.domain.entity.Pedido;
import com.dosreis.domain.entity.PerfilUsuario;
import com.dosreis.domain.entity.Produto;
import com.dosreis.domain.entity.Usuario;
import com.dosreis.domain.enums.StatusPedido;
import com.dosreis.domain.enums.UserRole;
import com.dosreis.domain.repository.ClienteRepository;
import com.dosreis.domain.repository.PedidoRepository;
import com.dosreis.domain.repository.ProdutoRepository;
import com.dosreis.domain.repository.UsuarioRepository;

@Configuration
@Profile("developement")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente(null, "Sarah", "47324239811");
		Cliente c2 = new Cliente(null, "Erick", "46090833886");
		
		clienteRepo.saveAll(Arrays.asList(c1, c2));
	
		
		Pedido p1 = new Pedido(null, StatusPedido.REALIZADO, c1, LocalDate.now(), new BigDecimal(100));
		Pedido p2 = new Pedido(null, StatusPedido.REALIZADO, c1, LocalDate.now(), new BigDecimal(99.80));
		pedidoRepo.saveAll(Arrays.asList(p1, p2));
		
		Produto pr1 = new Produto(null, "Amendoas", 12.45);
		Produto pr2 = new Produto(null, "Balões", 20.0);
		produtoRepo.saveAll(Arrays.asList(pr1, pr2));
		
		PerfilUsuario pu1 = new PerfilUsuario(null, "Sarah", "sarah123@gmail.com", "46090833886", "Masculino", "11", "11965869482");
		Usuario u1 = new Usuario("Usuario", new BCryptPasswordEncoder().encode("user123"),UserRole.ADMIN, pu1);
		
		PerfilUsuario pu2 = new PerfilUsuario(null, "Erick", "erick123@gmail.com", "47324239811", "Masculino", "11", "11965869482");
		Usuario u2 = new Usuario("Erick", new BCryptPasswordEncoder().encode("erick123"),UserRole.ADMIN, pu2);
		
		
		usuarioRepo.saveAll(Arrays.asList(u1,u2));
		
	}

}
