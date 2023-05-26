package com.dosreis.service;

import java.util.Optional;

import com.dosreis.domain.entity.Pedido;
import com.dosreis.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
}
