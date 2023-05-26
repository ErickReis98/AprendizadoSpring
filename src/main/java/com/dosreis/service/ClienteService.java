package com.dosreis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.dosreis.domain.entity.Cliente;
import com.dosreis.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	public Cliente salvar(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	public List<Cliente> listarTodos() {
		return clienteRepo.findAll();
	}

	public Object findById(Integer id) {
		verificaId(id);
		return clienteRepo.findById(id);
	}

	public void deletar(Integer id) {
		verificaId(id);
		clienteRepo.deleteById(id);
	}

	public Cliente alterar(Integer id, Cliente cliente) {
		verificaId(id);
		Cliente c = clienteRepo.getReferenceById(id);
		alterarCliente(c, cliente);
		return cliente = clienteRepo.save(c);
	}

	public void alterarCliente(Cliente c, Cliente cliente) {
		c.setNome(cliente.getNome());
	}

	public void verificaId(Integer id) {
		if (!clienteRepo.existsById(id)) {
			System.out.println("Cliente inexistente.");
		}
	}

	public List<Cliente> buscaPorNome(String nome) {
		return clienteRepo.buscaPorNome(nome);
	}

	public Cliente buscaPorCpf(String cpf) {
		return clienteRepo.buscaPorCpf(cpf);
	}

	//Metodo para utilizar filtros dinamicos usando o tipo Containing
	public List<Cliente> buscaPorFiltro(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clienteRepo.findAll(example);
		return lista;
	}
}
