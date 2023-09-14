package com.dosreis.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Setter;

@Entity
@Table(name = "tb_item_pedido")


public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido idPedido;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto idProduto;

	private Integer quantidade;

	private Double subTotal;

	public Integer getId() {
		return id;
	}

	public Pedido getIdPedido() {
		return idPedido;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getSubTotal() {
		return quantidade.doubleValue() * idProduto.getPreco();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdPedido(Pedido idPedido) {
		this.idPedido = idPedido;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
	

}
