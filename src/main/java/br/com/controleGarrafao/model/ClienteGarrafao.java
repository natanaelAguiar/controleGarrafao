package br.com.controleGarrafao.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "CLIENTES_GARRAFAOS")
public class ClienteGarrafao{
	private long id;
	private Cliente cliente;
	private Garrafao garrafao;
	private int quantidade;
	
	@Id
    @GeneratedValue
    @Column(name = "CLIENTE_GARRAFAO_ID")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLIENTE_ID")
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "GARRAFAO_ID")
	public Garrafao getGarrafao() {
		return garrafao;
	}
	
	public void setGarrafao(Garrafao garrafao) {
		this.garrafao = garrafao;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}