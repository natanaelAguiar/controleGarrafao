package br.com.sasoriengine.controlegarrafao.model;


public class ClienteGarrafaoIdDTO implements java.io.Serializable{
	private ClienteDTO cliente;
	private GarrafaoDTO garrafao;
	
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public GarrafaoDTO getGarrafao() {
		return garrafao;
	}
	public void setGarrafao(GarrafaoDTO garrafao) {
		this.garrafao = garrafao;
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteGarrafaoIdDTO that = (ClienteGarrafaoIdDTO) o;

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
