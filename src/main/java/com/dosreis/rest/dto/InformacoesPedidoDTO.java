package com.dosreis.rest.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder

@AllArgsConstructor
@NoArgsConstructor
public class InformacoesPedidoDTO {

	private Integer id;
	private String cpf;
	private String nomeCliente;
	private BigDecimal total;
	private String dataPedido;
	private String status;
	private List<InformacaoItemPedidoDTO> items;

	public BigDecimal totalPedido() {
		double soma = 0;
		for(InformacaoItemPedidoDTO x : items) {
			 soma = soma + x.getSubTotal();
		}
		return new BigDecimal(soma).setScale(2, RoundingMode.HALF_EVEN);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getTotal() {
		return totalPedido();
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<InformacaoItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<InformacaoItemPedidoDTO> items) {
		this.items = items;
	}

	
}
