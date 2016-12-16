package br.com.controleGarrafao.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "cliente_garrafao", catalog = "controleGarrafao")
@AssociationOverrides({
	@AssociationOverride(name = "pk.cliente", joinColumns = @JoinColumn(name = "CLIENTE_ID")),
	@AssociationOverride(name = "pk.garrafao", joinColumns = @JoinColumn(name = "GARRAFAO_ID"))
})
public class ClienteGarrafao implements Serializable {

	private ClienteGarrafaoId pk = new ClienteGarrafaoId();
	private int quantidade;
	
	public ClienteGarrafao(){}

	@EmbeddedId
	public ClienteGarrafaoId getPk() {
		return pk;
	}

	public void setPk(ClienteGarrafaoId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Cliente getCliente(){
		return getPk().getCliente();
	}
	
	public void setCliente(Cliente cliente){
		getPk().setCliente(cliente);
	}
	
	@Transient
	public Garrafao getGarrafao(){
		return getPk().getGarrafao();
	}
	
	public void setGarrafao(Garrafao garrafao){
		getPk().setGarrafao(garrafao);
	}

	@Column(name = "CLIENTE_QUANTIDADE")
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		ClienteGarrafao that = (ClienteGarrafao) o;
		
		if(getPk() != null ? !getPk().equals(that.getPk()): that.getPk() != null) return false;
		
		return true;
	}
	
	
}
