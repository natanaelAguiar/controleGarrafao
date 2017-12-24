package br.com.sasoriengine.controlegarrafao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	private long usuarioId;
	private String usuarioNome;
	private String usuarioPassword;
	private String usuarioRole;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USUARIO_ID", unique = true, nullable = false)
	public long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	@Column(name = "USUARIO_NOME", unique = true, nullable = false)
	public String getUsuarioNome() {
		return usuarioNome;
	}
	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}
	
	@Column(name = "USUARIO_PASSWORD", unique = true, nullable = false)
	public String getUsuarioPassword() {
		return usuarioPassword;
	}
	public void setUsuarioPassword(String usuarioPassword) {
		this.usuarioPassword = usuarioPassword;
	}
	
	@Column(name = "USUARIO_ROLE")
	public String getRole() {
		return usuarioRole;
	}
	public void setRole(String role) {
		this.usuarioRole = role;
	}
	
}
