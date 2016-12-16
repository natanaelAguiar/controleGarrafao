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
@Table(name = "cliente", catalog="controleGarrafao", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"CLIENTE_RUA", "CLIENTE_NUMERO", "CLIENTE_COMPLEMENTO"})
})
public class Cliente implements Serializable{

	private Integer clienteId;
	private String clienteNome;
	private String clienteRua;
	private int clienteNumero;
	private String clienteComplemento;
	private Set<ClienteGarrafao> clienteGarrafaos = new HashSet<ClienteGarrafao>(0);
	
	public Cliente(){}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLIENTE_ID", unique = true, nullable = false)
	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.cliente", cascade = CascadeType.ALL )
	public Set<ClienteGarrafao> getClienteGarrafaos() {
		return clienteGarrafaos;
	}

	public void setClienteGarrafaos(Set<ClienteGarrafao> clienteGarrafaos) {
		this.clienteGarrafaos = clienteGarrafaos;
	}
	
	
}
