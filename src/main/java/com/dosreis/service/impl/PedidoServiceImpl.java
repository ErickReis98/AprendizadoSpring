package com.dosreis.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dosreis.entity.Cliente;
import com.dosreis.entity.ItemPedido;
import com.dosreis.entity.Pedido;
import com.dosreis.entity.Produto;
import com.dosreis.exception.RegraNegocioException;
import com.dosreis.repository.ClienteRepository;
import com.dosreis.repository.ItemPedidoRepository;
import com.dosreis.repository.PedidoRepository;
import com.dosreis.repository.ProdutoRepository;
import com.dosreis.rest.dto.ItemPedidoDTO;
import com.dosreis.rest.dto.PedidoDTO;
import com.dosreis.service.PedidoService;

import jakarta.transaction.Transactional;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienteRepo.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente invalido."));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setData_pedido(LocalDate.now());
		pedido.setIdCliente(cliente);
		pedidoRepo.save(pedido);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		itemPedidoRepo.saveAll(itemsPedido);
		pedido.setItemPedidos(itemsPedido);
		return pedido;
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Não é possivel realizar um pedido sem itens.");
		}

		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtoRepo.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código de produto inválido: "));

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setIdPedido(pedido);
			itemPedido.setIdProduto(produto);
			return itemPedido;

		}).collect(Collectors.toList());
	}

}
