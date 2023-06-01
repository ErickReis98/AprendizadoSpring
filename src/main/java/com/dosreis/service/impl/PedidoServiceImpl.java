package com.dosreis.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dosreis.domain.entity.Cliente;
import com.dosreis.domain.entity.ItemPedido;
import com.dosreis.domain.entity.Pedido;
import com.dosreis.domain.entity.Produto;
import com.dosreis.domain.enums.StatusPedido;
import com.dosreis.domain.repository.ClienteRepository;
import com.dosreis.domain.repository.ItemPedidoRepository;
import com.dosreis.domain.repository.PedidoRepository;
import com.dosreis.domain.repository.ProdutoRepository;
import com.dosreis.exception.PedidoNaoEncontradoException;
import com.dosreis.exception.RegraNegocioException;
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
	public Pedido salvar(PedidoDTO pedidoDTO) {
		Integer idCliente = pedidoDTO.getCliente();
		Cliente cliente = clienteRepo.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente invalido."));

		Pedido pedido = new Pedido();
		pedido.setTotal(pedidoDTO.getTotal());
		pedido.setData_pedido(LocalDate.now());
		pedido.setIdCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		pedidoRepo.save(pedido);

		List<ItemPedido> itemsPedido = converterItems(pedido, pedidoDTO.getItems());
		itemPedidoRepo.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
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

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidoRepo.findByIdFecthItens(id);
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
		pedidoRepo.findById(id).map(pedido -> {
			pedido.setStatus(statusPedido);
			return pedidoRepo.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException() );

	}

}
