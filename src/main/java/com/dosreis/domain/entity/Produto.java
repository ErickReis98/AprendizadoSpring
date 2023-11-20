package com.dosreis.domain.entity;

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
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_produto")

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "{campo.nomeProduto.obrigatorio}")
	private String nomeProduto;

	@Column(name = "preco_unitario")
	@NotNull(message = "{campo.preco.obrigatorio}")
	private Double preco;

	@Column
	@NotNull
	private int estoque;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idProduto")
	private List<ItemPedido> items;

	public Produto(Integer id, @NotBlank(message = "{campo.nomeProduto.obrigatorio}") String nomeProduto,
			@NotNull(message = "{campo.preco.obrigatorio}") Double preco, @NotBlank Integer estoque) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.estoque = estoque;
	}
	
	

}
