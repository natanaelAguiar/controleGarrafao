package br.com.sasoriengine.controlegarrafao.model;

import java.util.HashSet;
import java.util.Set;

public class GarrafaoDTO {
	private long garrafaoId;
	private String garrafaoNome;
	private Set<ClienteGarrafaoDTO> clienteGarrafaosDTO = new HashSet<ClienteGarrafaoDTO>();
	
	public long getGarrafaoId() {
		return garrafaoId;
	}
	public void setGarrafaoId(long garrafaoId) {
		this.garrafaoId = garrafaoId;
	}
	public String getGarrafaoNome() {
		return garrafaoNome;
	}
	public void setGarrafaoNome(String garrafaoNome) {
		this.garrafaoNome = garrafaoNome;
	}
	public Set<ClienteGarrafaoDTO> getClienteGarrafaos() {
		return clienteGarrafaosDTO;
	}
	public void setClienteGarrafaos(Set<ClienteGarrafaoDTO> clienteGarrafaosDTO) {
		this.clienteGarrafaosDTO = clienteGarrafaosDTO;
	}
	
}
