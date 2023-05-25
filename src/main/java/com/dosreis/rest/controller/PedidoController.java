package com.dosreis.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dosreis.entity.Pedido;
import com.dosreis.rest.dto.PedidoDTO;
import com.dosreis.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoServ;
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody PedidoDTO dto) {
		return ResponseEntity.ok().body(pedidoServ.salvar(dto));
	}
	
	
	
}
