package com.dosreis.rest.controller;

import java.util.List;

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

import com.dosreis.entity.Produto;
import com.dosreis.service.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoServ;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
		return ResponseEntity.ok().body(produtoServ.salvar(produto));
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos(){
		return ResponseEntity.ok().body(produtoServ.listarTodos());
	}
	
	@GetMapping(value = "/findId/{id}")
	public ResponseEntity<Object> buscaPorId(@PathVariable Integer id){
		return ResponseEntity.ok().body(produtoServ.buscaPorId(id));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Produto> deletar(@PathVariable Integer id){
		produtoServ.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/findPreco/min={min}&max={max}")
	public ResponseEntity<List<Produto>> buscaPorFaixaPreco(@PathVariable Double min, @PathVariable Double max){
		return ResponseEntity.ok().body(produtoServ.buscaPorFaixaPreco(min, max));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> alterar(@PathVariable Integer id, @RequestBody Produto produto){
		return ResponseEntity.ok().body(produtoServ.alterar(produto, id));
	}
	
}
