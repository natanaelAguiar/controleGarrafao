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

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "garrafao")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "garrafaoId")
public class Garrafao {

	private long garrafaoId;
	private String garrafaoNome;
	private Set<ClienteGarrafao> clienteGarrafaos = new HashSet<ClienteGarrafao>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GARRAFAO_ID", unique = true, nullable = false)
	public long getGarrafaoId() {
		return garrafaoId;
	}

	public void setGarrafaoId(long garrafaoId) {
		this.garrafaoId = garrafaoId;
	}

	@Column(name = "GARRAFAO_NOME", nullable = false)
	@NotBlank
	public String getGarrafaoNome() {
		return garrafaoNome;
	}

	public void setGarrafaoNome(String garrafaoNome) {
		this.garrafaoNome = garrafaoNome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.garrafao", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH, CascadeType.DETACH }, orphanRemoval = true)
	public Set<ClienteGarrafao> getClienteGarrafaos() {
		return this.clienteGarrafaos;
	}

	public void setClienteGarrafaos(Set<ClienteGarrafao> clienteGarrafaos) {
		this.clienteGarrafaos = clienteGarrafaos;
	}

}
