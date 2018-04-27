package br.com.sasoriengine.controlegarrafao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="cliente")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "clienteId")
public class Cliente{
	private long clienteId;
	private String clienteNome;
	private String clienteRua;
	private int clienteNumero;
	private String clienteComplemento;
	private Set<ClienteGarrafao> clienteGarrafaos = new HashSet<ClienteGarrafao>(); 
	
	public Cliente() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID", unique = true, nullable = false)
	public long getClienteId() {
		return clienteId;
	}
	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
	}
	
	@Column(name = "CLIENTE_NOME")
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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "pk.cliente", cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	public Set<ClienteGarrafao> getClienteGarrafaos(){
		return this.clienteGarrafaos;
	}
	
	public void setClienteGarrafaos(Set<ClienteGarrafao> clienteGarrafaos) {
		this.clienteGarrafaos = clienteGarrafaos;
	}
	
}