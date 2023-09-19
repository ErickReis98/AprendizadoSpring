package com.dosreis.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.dosreis.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	UserDetails findByUsername(String username);
}
