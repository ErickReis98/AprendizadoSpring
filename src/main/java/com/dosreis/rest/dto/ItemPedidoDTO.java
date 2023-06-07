package com.dosreis.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {

	private Integer produto;
	private Integer quantidade;
	private Double Total;
	
	public ItemPedidoDTO() {

	}

	public ItemPedidoDTO(Integer produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}


}
