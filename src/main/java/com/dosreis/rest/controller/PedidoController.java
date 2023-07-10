package com.dosreis.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dosreis.domain.entity.ItemPedido;
import com.dosreis.domain.entity.Pedido;
import com.dosreis.domain.enums.StatusPedido;
import com.dosreis.rest.dto.AtualizacaoStatusPedidoDTO;
import com.dosreis.rest.dto.InformacaoItemPedidoDTO;
import com.dosreis.rest.dto.InformacoesPedidoDTO;
import com.dosreis.rest.dto.PedidoDTO;
import com.dosreis.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoServ;
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Integer salvar(@Valid @RequestBody PedidoDTO dto) {
		Pedido pedido = pedidoServ.salvar(dto);
        return pedido.getId();
	}
	
	@GetMapping(value = "/{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoServ.obterPedidoCompleto(id)
				.map( p -> converter(p))
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
	}

	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
		.builder()
		.id(pedido.getId())
		.dataPedido(pedido.getData_pedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
		.cpf(pedido.getIdCliente().getCpf())
		.nomeCliente(pedido.getIdCliente().getNome())
		.total(pedido.getTotal())
		.status(pedido.getStatus().name())
		.items(converter(pedido.getItens()))
		.build();
	}
	
	private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(item -> InformacaoItemPedidoDTO
				.builder()
				.nomeProduto(item.getIdProduto().getNomeProduto())
				.precoUnitario(item.getIdProduto().getPreco())
				.quantidade(item.getQuantidade())
				.subTotal(item.getSubTotal())
				.build()
		).collect(Collectors.toList());
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
		String novoStatus = dto.novoStatus();
		pedidoServ.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
}
