package com.dosreis.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.dosreis.domain.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pedido")
@Data
@NoArgsConstructor
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente idCliente;

	@Column(name = "data_pedido")
	private LocalDate data_pedido;

	@Column(precision = 20, scale = 2)
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status;
	
	@OneToMany(mappedBy = "idPedido", fetch = FetchType.LAZY)
	private List<ItemPedido> itens;

	public Pedido(Integer id, Cliente idCliente, LocalDate data_pedido, BigDecimal total) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.data_pedido = data_pedido;
		this.total = total;
	}

	

}
