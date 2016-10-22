package br.com.controleGarrafao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.JoinColumn;;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@TableGenerator(name="TABLE_GEN_CLIENTE", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
    valueColumnName="SEQ_COUNT", pkColumnValue="EMP_SEQ")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private Long id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "numero")
	private int numero;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="cliente_garrafao",  
    joinColumns={@JoinColumn(name="cliente_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="garrafao_id", referencedColumnName="id")})
	private List<Garrafao> garrafao = new ArrayList<Garrafao>();

	public Long getId() {
		return id;
	}

	public List<Garrafao> getGarrafao() {
		return garrafao;
	}

	public void setGarrafao(List<Garrafao> garrafao) {
		this.garrafao = garrafao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
