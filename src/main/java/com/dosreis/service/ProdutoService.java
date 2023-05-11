package com.dosreis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dosreis.entity.Produto;
import com.dosreis.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	public Produto salvar(Produto produto) {
		return produtoRepo.save(produto);
	}
	
	public List<Produto> listarTodos(){
		return produtoRepo.findAll();
	}
	
	public Object buscaPorId(Integer id) {
		verificaId(id);
		return produtoRepo.findById(id);
	}

	public void deletar(Integer id) {
		verificaId(id);
		produtoRepo.deleteById(id);
	}
	
	public Produto alterar(Produto produto, Integer id) {
		verificaId(id);
		Produto p = produtoRepo.getReferenceById(id);
		alterarProduto(p, produto);
		return produto = produtoRepo.save(p);
	}
	
	public void verificaId(Integer id) {
		if(!produtoRepo.existsById(id)) {
			System.out.println("Produto inexistente.");
		}
	}
	
	public void alterarProduto(Produto p, Produto produto) {
		p.setDescricao(produto.getDescricao());
		p.setPreco(produto.getPreco());
	}
	
	public List<Produto> buscaPorFaixaPreco(Double a, Double b){
		return produtoRepo.buscaPorFaixaPreco(a, b);
	}
}
