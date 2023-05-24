package com.dosreis.service;

import com.dosreis.dto.PedidoDTO;
import com.dosreis.entity.Pedido;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
}
