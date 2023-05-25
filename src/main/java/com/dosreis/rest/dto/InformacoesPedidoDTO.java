package com.dosreis.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class InformacoesPedidoDTO {

	private Integer id;
	private String cpf;
	private String nomeCliente;
	private BigDecimal total;
	private List<InformacaoItemPedidoDTO> items;

	public InformacoesPedidoDTO() {

	}

	public InformacoesPedidoDTO(Integer id, String cpf, String nomeCliente, BigDecimal total,
			List<InformacaoItemPedidoDTO> items) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
		this.total = total;
		this.items = items;
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
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<InformacaoItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<InformacaoItemPedidoDTO> items) {
		this.items = items;
	}

}
