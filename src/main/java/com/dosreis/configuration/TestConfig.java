package com.dosreis.configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dosreis.domain.entity.Cliente;
import com.dosreis.domain.entity.ItemPedido;
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
import com.dosreis.rest.controller.PedidoController;
import com.dosreis.rest.dto.InformacaoItemPedidoDTO;
import com.dosreis.rest.dto.InformacoesPedidoDTO;
import com.dosreis.rest.dto.ItemPedidoDTO;
import com.dosreis.rest.dto.PedidoDTO;

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
	
	@Autowired
	private PedidoController pedC;
	
	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente(null, "Sarah", "47324239811");
		Cliente c2 = new Cliente(null, "Erick", "46090833886");
		
		clienteRepo.saveAll(Arrays.asList(c1, c2));
	
		Produto pr1 = new Produto(null, "Amendoas", 12.45);
		Produto pr2 = new Produto(null, "Balões", 20.0);
		Produto pr3 = new Produto(null, "Guardanapos", 5.99);
		produtoRepo.saveAll(Arrays.asList(pr1, pr2, pr3));
		
		
		List<ItemPedidoDTO> item1 = new ArrayList<>();
		item1.add(new ItemPedidoDTO(pr1.getId(), 4, 0.0));
		item1.add(new ItemPedidoDTO(pr2.getId(), 12, 0.0));
		
		PedidoDTO ped1 = new PedidoDTO(c1.getId(),new BigDecimal(0), item1);
		pedC.salvarManualmente(ped1);
		
		List<ItemPedidoDTO> item2 = new ArrayList<>();
		item2.add(new ItemPedidoDTO(pr1.getId(), 14, 0.0));
		item2.add(new ItemPedidoDTO(pr2.getId(), 2, 0.0));
		item2.add(new ItemPedidoDTO(pr3.getId(), 23, 0.0));
		
		PedidoDTO ped2 = new PedidoDTO(c1.getId(),new BigDecimal(0), item2);
		pedC.salvarManualmente(ped2);
		
		
		List<ItemPedidoDTO> item3 = new ArrayList<>();
		item3.add(new ItemPedidoDTO(pr2.getId(), 3, 0.0));
		item3.add(new ItemPedidoDTO(pr3.getId(), 10, 0.0));
		
		PedidoDTO ped3 = new PedidoDTO(c1.getId(),new BigDecimal(0), item3);
		pedC.salvarManualmente(ped3);
		
		PerfilUsuario pu1 = new PerfilUsuario(null, "Sarah", "sarah123@gmail.com", "46090833886", "Masculino", "11", "11965869482");
		Usuario u1 = new Usuario("Usuario", new BCryptPasswordEncoder().encode("user123"),UserRole.ADMIN, pu1);
		
		PerfilUsuario pu2 = new PerfilUsuario(null, "Erick", "erick123@gmail.com", "47324239811", "Masculino", "11", "11965869482");
		Usuario u2 = new Usuario("Erick", new BCryptPasswordEncoder().encode("erick123"),UserRole.ADMIN, pu2);
		
		
		usuarioRepo.saveAll(Arrays.asList(u1,u2));
		
	}

}
