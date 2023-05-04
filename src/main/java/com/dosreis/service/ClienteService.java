package com.dosreis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dosreis.entity.Cliente;
import com.dosreis.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepo.save(cliente);
	}
	
	public List<Cliente> listarTodos(){
		return clienteRepo.findAll();
	}

	public Object findById(Integer id) {
		return clienteRepo.findById(id);
	}
	
	public void deletar(Integer id) {
		clienteRepo.deleteById(id);
	}
	
	public Cliente alterar(Integer id, Cliente cliente) {
		Cliente c = clienteRepo.getReferenceById(id);
			alterarCliente(c, cliente);
			return cliente = clienteRepo.save(c);
	}
	
	public void alterarCliente(Cliente c, Cliente cliente) {
		c.setNome(cliente.getNome());
	}
}
