package com.dosreis.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dosreis.entity.Cliente;
import com.dosreis.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteServ;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos(){
		return ResponseEntity.ok().body(clienteServ.listarTodos()); 
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
		return ResponseEntity.ok().body(clienteServ.salvar(cliente));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Integer id){
		return ResponseEntity.ok().body(clienteServ.findById(id));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> deletar(@PathVariable Integer id){
		clienteServ.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Integer id, @RequestBody Cliente cliente){
		return ResponseEntity.ok().body(clienteServ.alterar(id, cliente));
	}
}
