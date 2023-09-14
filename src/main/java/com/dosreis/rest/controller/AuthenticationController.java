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

import com.dosreis.domain.entity.PerfilUsuario;
import com.dosreis.domain.entity.Usuario;
import com.dosreis.rest.dto.AuthenticationDTO;
import com.dosreis.rest.dto.LoginResponseDTO;
import com.dosreis.rest.dto.PerfilUsuarioDTO;
import com.dosreis.rest.dto.RegisterDTO;
import com.dosreis.rest.dto.RegistroComPerfil;
import com.dosreis.service.impl.UsuarioService;
import com.dosreis.service.token.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UsuarioService usuarioServ;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Usuario) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
		
	}
	
	@PostMapping("/register")
	public ResponseEntity registrarSemPerfil(@Valid @RequestBody RegisterDTO data) {
		if( usuarioServ.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());
		this.usuarioServ.salvar(newUser);
		
		return ResponseEntity .ok().build();
		
	}
	
	@PostMapping("/registerPerfil")
	public ResponseEntity<Usuario> registrarComPerfil(@Valid @RequestBody RegistroComPerfil data) {
		
		if(usuarioServ.findByLogin(data.uDTO().login()) != null) 
			return ResponseEntity.badRequest().build();
		
		PerfilUsuario pU = new PerfilUsuario(null, data.puDTO().nome(), data.puDTO().email(), data.puDTO().cpf(), data.puDTO().genero(), data.puDTO().ddd(), data.puDTO().telefone());
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.uDTO().password());
		
		Usuario newUser = new Usuario(data.uDTO().login(), encryptedPassword, data.uDTO().role(), pU);
		this.usuarioServ.salvar(newUser);
		
		return ResponseEntity .ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Object> listarTodos(){
		return ResponseEntity.ok().body(usuarioServ.listarTodos());
	}
}