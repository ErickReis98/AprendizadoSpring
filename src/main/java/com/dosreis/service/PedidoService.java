package com.dosreis.service;

import com.dosreis.entity.Pedido;
import com.dosreis.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
}
