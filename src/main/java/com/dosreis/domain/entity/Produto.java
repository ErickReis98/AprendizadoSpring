package com.dosreis.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Campo nome do produto é obrigatório.")
	private String nomeProduto;

	@Column(name = "preco_unitario")
	@NotNull(message = "Campo preço é obrigatório.")
	private BigDecimal preco;

	@JsonIgnore
	@OneToMany(mappedBy = "idProduto")
	private List<ItemPedido> items;

	public Produto() {
		super();
	}

	public Produto(Integer id, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.nomeProduto = descricao;
		this.preco = preco;
	}



}
