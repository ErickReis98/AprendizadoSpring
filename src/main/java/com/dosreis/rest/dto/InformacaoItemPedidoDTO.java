package com.dosreis.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacaoItemPedidoDTO {

	private String nomeProduto;
	private Integer quantidade;
	private Double precoUnitario;
	private Double subTotal;
	

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getSubTotal() {
		return quantidade * precoUnitario.doubleValue();
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
	

}
