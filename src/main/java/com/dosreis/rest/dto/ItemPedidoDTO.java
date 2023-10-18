package com.dosreis.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ItemPedidoDTO {

	private Integer produto;
	private int quantidade;
	private Double Total;
	
}
