package br.com.sasoriengine.controlegarrafao.model;

import javax.persistence.EmbeddedId;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClienteGarrafaoDTO implements java.io.Serializable{

	private ClienteGarrafaoIdDTO pk = new ClienteGarrafaoIdDTO();
	int quantidade;
	
	public ClienteGarrafaoIdDTO getPk() {
		return pk;
	}
	public void setPk(ClienteGarrafaoIdDTO pk) {
		this.pk = pk;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	@JsonIgnore
	public GarrafaoDTO getGarrafaoDTO() {
		return getPk().getGarrafao();
	}
	public void setGarrafaoDTO(GarrafaoDTO garrafao) {
		getPk().setGarrafao(garrafao);
	}
	@JsonIgnore
	public ClienteDTO getClienteDTO() {
		return getPk().getCliente();
	}
	public void setClienteDTO(ClienteDTO clienteDTO) {
		getPk().setCliente(clienteDTO);
	}
}
