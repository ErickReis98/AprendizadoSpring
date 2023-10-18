package com.dosreis.service;

import java.util.List;
import java.util.Optional;

import com.dosreis.domain.entity.Pedido;
import com.dosreis.domain.enums.StatusPedido;
import com.dosreis.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizaStatus(Integer id, StatusPedido statusPedido);
	
}
