package br.com.controleGarrafao.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "GARRAFAOS")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "garrafaoId")
public class Garrafao {

	private long garrafaoId;
	private String garrafaoNome;
	@JsonBackReference
	private List<ClienteGarrafao> clienteGarrafaos = new ArrayList<ClienteGarrafao>();

	public Garrafao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "GARRAFAO_ID", unique = true, nullable = false)
	public long getGarrafaoId() {
		return garrafaoId;
	}

	public void setGarrafaoId(long garrafaoId) {
		this.garrafaoId = garrafaoId;
	}

	@Column(name = "GARRAFAO_NOME", nullable = false, unique = true)
	public String getGarrafaoNome() {
		return garrafaoNome;
	}

	public void setGarrafaoNome(String garrafaoNome) {
		this.garrafaoNome = garrafaoNome;
	}
	
	@OneToMany(mappedBy = "garrafao", fetch = FetchType.EAGER)
	public List<ClienteGarrafao> getClienteGarrafaos() {
		return clienteGarrafaos;
	}

	public void setClienteGarrafaos(List<ClienteGarrafao> clienteGarrafaos) {
		this.clienteGarrafaos = clienteGarrafaos;
	}
	
	public void addClienteGarrafao(ClienteGarrafao clienteGarrafao){
		this.clienteGarrafaos.add(clienteGarrafao);
	}

}
