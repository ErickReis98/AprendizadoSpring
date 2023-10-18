package com.dosreis.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.dosreis.domain.entity.ItemPedido;
import com.dosreis.domain.enums.StatusPedido;


public interface ClientecomPedidosDTO{

	 String getNome();
	 String getCpf();
	 LocalDate getData_pedido();
	 StatusPedido getStatus();
	 BigDecimal getTotal();
	 List<ItemPedido> getItens();
	 
	
	
}
