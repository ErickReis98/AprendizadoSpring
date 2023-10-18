package com.dosreis.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import com.dosreis.exception.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoDTO {

	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	
	private BigDecimal total;
	
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> listaItens;



	


}
