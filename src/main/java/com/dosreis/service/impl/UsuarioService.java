package com.dosreis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dosreis.domain.entity.Usuario;
import com.dosreis.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public Usuario salvar(Usuario usuario) {
		return usuarioRepo.save(usuario); 
	}

	public Object listarTodos() {
		return usuarioRepo.findAll();
	}

	public UserDetails findByLogin(String login) {
		return usuarioRepo.findByUsername(login);
	}

}
