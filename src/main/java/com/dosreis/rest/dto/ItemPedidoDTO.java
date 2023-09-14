package com.dosreis.rest.dto;

import lombok.Getter;
import lombok.Setter;


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

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getTotal() {
		return Total;
	}

	public void setTotal(Double total) {
		Total = total;
	}

	

}
