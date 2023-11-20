package com.dosreis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dosreis.domain.entity.Produto;
import com.dosreis.domain.repository.ProdutoRepository;
import com.dosreis.rest.dto.MascaraProdutoDTO;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Transactional
	public Produto salvar(MascaraProdutoDTO produto) {
		String precoStr = produto.preco();
		precoStr = precoStr.replace(".", "");
		precoStr = precoStr.replace("R$", "");
		precoStr = precoStr.replace(",", ".");
		precoStr = precoStr.substring(1);
		double preco = Double.parseDouble(precoStr);
		Produto p = new Produto();
		p.setNomeProduto(produto.nomeProduto());
		p.setPreco(preco);
		p.setEstoque(produto.estoque());
		
 		return produtoRepo.save(p);
	}
	
	@Transactional
	public List<Produto> listarTodos(){
		return produtoRepo.findAll();
	}
	
	@Transactional
	public Object buscaPorId(Integer id) {
		verificaId(id);
		return produtoRepo.findById(id);
	}

	@Transactional
	public void deletar(Integer id) {
		verificaId(id);
		produtoRepo.deleteById(id);
	}
	
	@Transactional
	public Produto alterar(Produto produto, Integer id) {
		verificaId(id);
		produto.setId(id);
		return produtoRepo.save(produto);
	}
	
	@Transactional
	public void verificaId(Integer id) {
		if(!produtoRepo.existsById(id)) {
			System.out.println("Produto inexistente.");
		}
	}
	
	@Transactional
	public List<Produto> buscaPorFaixaPreco(Double a, Double b){
		return produtoRepo.buscaPorFaixaPreco(a, b);
	}
}
