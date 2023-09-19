package com.dosreis.domain.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dosreis.domain.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "tb_usuario")
@Table(name = "tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "userId")
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private UserRole role;

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "perfil_usuario_id")
	private PerfilUsuario perfilUsuario;
	
	
	 public Usuario(Integer userId, String login, String password) {
		super();
		this.userId = userId;
		this.username = login;
		this.password = password;
	}

	

	public Usuario(String login, String password, UserRole role) {
		super();
		this.username = login;
		this.password = password;
		this.role = role;
	}

	public Usuario(String login, String password, UserRole role, PerfilUsuario perfilUsuario) {
		super();
		this.username = login;
		this.password = password;
		this.role = role;
		this.perfilUsuario = perfilUsuario;
	}

	@Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
	        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	    }

	    @Override
	    public String getPassword() {
	        return this.password;
	    }

	    @Override
	    public String getUsername() {
	        return this.username;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

		@Override
		public String toString() {
			return "Username: " + username + ",\nPassword: " + password;
		}



		
	    
	    
	}