package com.dosreis.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.dosreis.exception.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


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

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}
	


}
