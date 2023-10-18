package com.dosreis.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dosreis.domain.entity.Cliente;
import com.dosreis.domain.entity.Pedido;
import com.dosreis.rest.dto.ClientecomPedidosDTO;
import com.dosreis.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteService clienteServ;
	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos(){
		return ResponseEntity.ok().body(clienteServ.listarTodos()); 
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente){
		return ResponseEntity.ok().body(clienteServ.salvar(cliente));
	}
	
	@GetMapping(value = "/findId/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Integer id){
		return ResponseEntity.ok().body(clienteServ.findById(id));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> deletar(@PathVariable Integer id){
		clienteServ.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> alterar(@Valid @PathVariable Integer id, @RequestBody Cliente cliente){
		return ResponseEntity.ok().body(clienteServ.alterar(id, cliente));
	}
	
	@GetMapping(value = "/findNome/{nome}")
	public ResponseEntity<List<Cliente>> buscaPorNome(@PathVariable String nome){
		return ResponseEntity.ok().body(clienteServ.buscaPorNome(nome));
	}
	
	@GetMapping(value = "/findCpf/{cpf}")
	public ResponseEntity<Cliente> buscaPorCpf(@PathVariable String cpf){
		return ResponseEntity.ok().body(clienteServ.buscaPorCpf(cpf));
	}
	
	@GetMapping(value = "/findFiltro")
	public ResponseEntity<List<Cliente>> buscaPorFiltro(Cliente filtro){
		return ResponseEntity.ok(clienteServ.buscaPorFiltro(filtro)); 
	}
	
	
	
	// Rota para buscar os pedidos de um determinado cliente
	@GetMapping(value = "/buscarPedidos/{id}")
	public ResponseEntity<List<ClientecomPedidosDTO>> buscarPedidos(@PathVariable Integer id){
		return ResponseEntity.ok(clienteServ.buscarPedidos(id));
		
	}
}
