package br.com.controleGarrafao.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	private long clienteId;
	private String clienteNome;
	private String clienteRua;
	private int clienteNumero;
	private String clienteComplemento;
	
	private Set<ClienteGarrafao> clienteGarrafaos = new HashSet<ClienteGarrafao>();

	public Cliente() {
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLIENTE_ID", unique = true, nullable = false)
	public long getClienteId() {
		return clienteId;
	}

	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
	}

	@Column(name = "CLIENTE_NOME", nullable = false)
	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	@Column(name = "CLIENTE_RUA", nullable = false)
	public String getClienteRua() {
		return clienteRua;
	}

	public void setClienteRua(String clienteRua) {
		this.clienteRua = clienteRua;
	}

	@Column(name = "CLIENTE_NUMERO", nullable = false)
	public int getClienteNumero() {
		return clienteNumero;
	}

	public void setClienteNumero(int clienteNumero) {
		this.clienteNumero = clienteNumero;
	}

	@Column(name = "CLIENTE_COMPLEMENTO")
	public String getClienteComplemento() {
		return clienteComplemento;
	}

	public void setClienteComplemento(String clienteComplemento) {
		this.clienteComplemento = clienteComplemento;
	}
	
	@OneToMany(mappedBy = "cliente")
	public Set<ClienteGarrafao> getClienteGarrafao() {
		return clienteGarrafaos;
	}

	public void setClienteGarrafao(Set<ClienteGarrafao> clienteGarrafao) {
		this.clienteGarrafaos = clienteGarrafao;
	}
	
	public void addClienteGarrafao(ClienteGarrafao clienteGarrafao) {
		this.clienteGarrafaos.add(clienteGarrafao);
	}
}
