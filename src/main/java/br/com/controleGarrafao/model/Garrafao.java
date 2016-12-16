package br.com.controleGarrafao.model;

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

@Entity
@Table(name = "garrafao", catalog = "controleGarrafao")
public class Garrafao{

	private Integer garrafaoId;
	private String garrafaoNome;
	private Set<ClienteGarrafao> clienteGarrafaos = new HashSet<ClienteGarrafao>(0);

	public Garrafao() {}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "GARRAFAO_ID", unique = true, nullable = false)
	public Integer getGarrafaoId() {
		return garrafaoId;
	}

	public void setGarrafaoId(Integer garrafaoId) {
		this.garrafaoId = garrafaoId;
	}

	@Column(name = "GARRAFAO_NOME", nullable = false, unique = true)
	public String getGarrafaoNome() {
		return garrafaoNome;
	}

	public void setGarrafaoNome(String garrafaoNome) {
		this.garrafaoNome = garrafaoNome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.garrafao")
	public Set<ClienteGarrafao> getClienteGarrafaos() {
		return clienteGarrafaos;
	}

	public void setClienteGarrafaos(Set<ClienteGarrafao> clienteGarrafaos) {
		this.clienteGarrafaos = clienteGarrafaos;
	}
	
	
	
	
}
