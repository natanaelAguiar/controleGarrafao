package br.com.sasoriengine.controlegarrafao.model;

import java.util.HashSet;
import java.util.Set;

public class ClienteDTO {
	private Integer clienteId;
	private String clienteNome;
	private String clienteRua;
	private int clienteNumero;
	private String clienteComplemento;
	private Set<ClienteGarrafaoDTO> clienteGarrafaosDTO = new HashSet<ClienteGarrafaoDTO>();
	
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public String getClienteNome() {
		return clienteNome;
	}
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	public String getClienteRua() {
		return clienteRua;
	}
	public void setClienteRua(String clienteRua) {
		this.clienteRua = clienteRua;
	}
	public int getClienteNumero() {
		return clienteNumero;
	}
	public void setClienteNumero(int clienteNumero) {
		this.clienteNumero = clienteNumero;
	}
	public String getClienteComplemento() {
		return clienteComplemento;
	}
	public void setClienteComplemento(String clienteComplemento) {
		this.clienteComplemento = clienteComplemento;
	}
	public Set<ClienteGarrafaoDTO> getClienteGarrafaos() {
		return clienteGarrafaosDTO;
	}
	public void setClienteGarrafaos(Set<ClienteGarrafaoDTO> clienteGarrafaosDTO) {
		this.clienteGarrafaosDTO = clienteGarrafaosDTO;
	} 
}
