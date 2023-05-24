package com.dosreis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dosreis.dto.PedidoDTO;
import com.dosreis.entity.Pedido;
import com.dosreis.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoServ;
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Integer salvar(@RequestBody PedidoDTO dto) {
		Pedido pedido = pedidoServ.salvar(dto);
		return pedido.getId();
	}
}
