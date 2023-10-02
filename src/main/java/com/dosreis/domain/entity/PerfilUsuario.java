package com.dosreis.domain.entity;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_perfilusuario")
@Table(name = "tb_perfilusuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilUsuario {

	



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@Email
	@Column
	private String email;
	
	@CPF
	@Column
	private String cpf;
	
	@Column
	private String genero;
	
	@Column
	private String ddd;
	
	@Column
	private String telefone;
	
	
	
}
