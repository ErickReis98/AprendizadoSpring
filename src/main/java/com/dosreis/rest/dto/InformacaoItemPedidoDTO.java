package com.dosreis.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacaoItemPedidoDTO {

	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Integer quantidade;

	

}
