package com.dosreis.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dosreis.domain.entity.Usuario;
import com.dosreis.rest.dto.AuthenticationDTO;
import com.dosreis.rest.dto.ResgisterDTO;
import com.dosreis.service.impl.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UsuarioService usuarioServ;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authManager.authenticate(usernamePassword);
		
		
		return ResponseEntity.ok().build();
		
	}
	
	@PostMapping("/register")
	public ResponseEntity registrar(@Valid @RequestBody ResgisterDTO data) {
		if(this.usuarioServ.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());
		this.usuarioServ.salvar(newUser);
		
		return ResponseEntity .ok().build();
		
	}
	
	@GetMapping
	public ResponseEntity<Object> listarTodos(){
		return ResponseEntity.ok().body(usuarioServ.listarTodos());
	}
}
