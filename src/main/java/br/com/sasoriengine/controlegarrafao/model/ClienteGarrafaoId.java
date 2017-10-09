package br.com.sasoriengine.controlegarrafao.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ClienteGarrafaoId implements java.io.Serializable{
	
	private Cliente cliente;
	private Garrafao garrafao;
	
	@ManyToOne
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@ManyToOne
	public Garrafao getGarrafao() {
		return garrafao;
	}
	public void setGarrafao(Garrafao garrafao) {
		this.garrafao = garrafao;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteGarrafaoId that = (ClienteGarrafaoId) o;

        if (cliente != null ? !cliente.equals(that.cliente) : that.cliente != null) return false;
        if (garrafao != null ? !garrafao.equals(that.garrafao) : that.garrafao != null)
            return false;

        return true;
	}
	
	public int hashCode() {
        int result;
        result = (cliente != null ? cliente.hashCode() : 0);
        result = 31 * result + (garrafao != null ? garrafao.hashCode() : 0);
        return result;
    }
	
}
