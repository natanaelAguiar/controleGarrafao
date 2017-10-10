package br.com.sasoriengine.controlegarrafao.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.sasoriengine.controlegarrafao.exeption.ClienteNotFoundException;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

public class ClienteGarrafaoBOImp implements ClienteGarrafaoBO {
	private ClienteGarrafaoDAO clienteGarrafaoDAO;

	public ClienteGarrafaoBOImp(ClienteGarrafaoDAO clienteGarrafaoDAO) {
		this.clienteGarrafaoDAO = clienteGarrafaoDAO;
	}

	public List<ClienteDTO> findAllCliente() {

		return this.clienteGarrafaoDAO.findAllCliente();
	}

	public List<GarrafaoDTO> findAllGarrafao() {

		return clienteGarrafaoDAO.findAllGarrafao();
	}

	public ResponseEntity<ClienteDTO> findClienteById(Long id) {
		ClienteDTO clienteDTO = new ClienteDTO();
		if (id > 0) {
			try {
				clienteDTO = clienteGarrafaoDAO.findClienteById(id);
			} catch (ObjectNotFoundException e) {
				return new ResponseEntity<ClienteDTO>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<ClienteDTO>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<GarrafaoDTO> findGarrafaoById(Long id) {
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		if (id > 0) {
			try {
				garrafaoDTO = clienteGarrafaoDAO.findGarrafaoById(id);
			} catch (ObjectNotFoundException e) {
				return new ResponseEntity<GarrafaoDTO>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<GarrafaoDTO>(garrafaoDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<GarrafaoDTO>(HttpStatus.BAD_REQUEST);
	}
}