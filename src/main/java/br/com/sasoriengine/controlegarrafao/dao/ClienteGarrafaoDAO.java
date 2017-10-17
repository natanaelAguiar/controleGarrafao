package br.com.sasoriengine.controlegarrafao.dao;

import java.util.List;

import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

public interface ClienteGarrafaoDAO {

	public List<ClienteDTO> findAllCliente();
	public List<GarrafaoDTO> findAllGarrafao();
	public ClienteDTO findClienteById(Long id);
	public GarrafaoDTO findGarrafaoById(Long id);
	public ClienteDTO saveOrUpdateCliente(Cliente cliente);
	public GarrafaoDTO saveOrUpdateGarrafao(Garrafao garrafao);
	public boolean removeCliente(Long id);
}
