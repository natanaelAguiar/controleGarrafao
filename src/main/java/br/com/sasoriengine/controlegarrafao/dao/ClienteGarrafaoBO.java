package br.com.sasoriengine.controlegarrafao.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

public interface ClienteGarrafaoBO {
	public List<ClienteDTO> findAllCliente();
	public List<GarrafaoDTO> findAllGarrafao();
	public ResponseEntity<ClienteDTO> findClienteById(Long id);
	public ResponseEntity<GarrafaoDTO> findGarrafaoById(Long id);
	public ResponseEntity<ClienteDTO> saveOrUpdateCliente(Cliente cliente);
	public ResponseEntity<GarrafaoDTO> saveOrUpdateGarrafao(Garrafao garrafao);
}
