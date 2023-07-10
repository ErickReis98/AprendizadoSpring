package com.dosreis.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.dosreis.exception.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	
	private BigDecimal total;
	
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> items;

	public PedidoDTO() {

	}

	public PedidoDTO(Integer cliente, BigDecimal total, List<ItemPedidoDTO> items) {
		super();
		this.cliente = cliente;
		this.total = total;
		this.items = items;
	}
	


}
