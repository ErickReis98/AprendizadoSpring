package com.dosreis.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pedido")

@NoArgsConstructor
public class Pedido {

	@Id
	@Column(name = "id_pedido")
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

	public Pedido(Integer id, StatusPedido status, Cliente idCliente, LocalDate data_pedido, BigDecimal total) {
		super();
		this.id = id;
		this.status = status;
		this.idCliente = idCliente;
		this.data_pedido = data_pedido;
		this.total = total;
	}

	public Pedido(Integer id, StatusPedido status, Cliente idCliente, LocalDate data_pedido, BigDecimal total, 
			List<ItemPedido> itens) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.data_pedido = data_pedido;
		this.total = total;
		this.status = status;
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDate getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}

	public BigDecimal getTotal() {
		return totalPedido();
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public BigDecimal totalPedido() {
		double soma = 0;
		for(ItemPedido x : itens) {
			 soma = soma + x.getSubTotal();
		}
		return new BigDecimal(soma).setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idCliente=" + idCliente + ", data_pedido=" + data_pedido + ", total=" + total
				+ ", status=" + status + ", itens=" + itens + ", totalPedido()=" + totalPedido() + "]";
	}
	
	
}
