package br.com.sasoriengine.controlegarrafao.dao;

import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

public class ClienteGarrafaoBOImp implements ClienteGarrafaoBO {
	private ClienteGarrafaoDAO clienteGarrafaoDAO;

	public ClienteGarrafaoBOImp(ClienteGarrafaoDAO clienteGarrafaoDAO) {
		this.clienteGarrafaoDAO = clienteGarrafaoDAO;
	}

	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		try {
			return new ResponseEntity<List<ClienteDTO>>(this.clienteGarrafaoDAO.findAllCliente(), HttpStatus.OK);
		}catch (HibernateException e) {
			return new ResponseEntity<List<ClienteDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<GarrafaoDTO>> findAllGarrafao() {
		try {
			
			return new ResponseEntity<List<GarrafaoDTO>>(clienteGarrafaoDAO.findAllGarrafao(), HttpStatus.OK);
		}catch (HibernateException e) {
			return new ResponseEntity<List<GarrafaoDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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

	public ResponseEntity<ClienteDTO> saveOrUpdateCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		try {
			if(cliente.getClienteNome() != null && cliente.getClienteGarrafaos().size() > 0 && cliente.getClienteNumero() > 0
					&& cliente.getClienteRua() != null) {
				clienteDTO = clienteGarrafaoDAO.saveOrUpdateCliente(cliente);
			return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
			}else new ResponseEntity<ClienteDTO>(HttpStatus.BAD_REQUEST);
			
		} catch (ValidationException e) {			
			return new ResponseEntity<ClienteDTO>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ClienteDTO>(HttpStatus.SERVICE_UNAVAILABLE);
	}

	public ResponseEntity<GarrafaoDTO> saveOrUpdateGarrafao(Garrafao garrafao) {
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		try {
			if(garrafao.getGarrafaoNome() != null) {
				garrafaoDTO = clienteGarrafaoDAO.saveOrUpdateGarrafao(garrafao);
			return new ResponseEntity<GarrafaoDTO>(garrafaoDTO, HttpStatus.OK);
			}else new ResponseEntity<ClienteDTO>(HttpStatus.BAD_REQUEST);
			
		} catch (ValidationException e) {			
			return new ResponseEntity<GarrafaoDTO>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<GarrafaoDTO>(HttpStatus.SERVICE_UNAVAILABLE);
	}

	public ResponseEntity<ClienteDTO> removeClienteById(long id) {
		try {
			boolean b;
			if(id > 0) {
				b = clienteGarrafaoDAO.removeClienteById(id);
				if(b)
				return new ResponseEntity<ClienteDTO>(HttpStatus.OK);
				else
					return new ResponseEntity<ClienteDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}else
				return new ResponseEntity<ClienteDTO>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<ClienteDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<GarrafaoDTO> removeGarrafaoById(long id) {
		try {
			boolean b;
			if(id > 0) {
				b = clienteGarrafaoDAO.removeGarrafaoById(id);
				if(b)
				return new ResponseEntity<GarrafaoDTO>(HttpStatus.OK);
				else
					return new ResponseEntity<GarrafaoDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			}else
				return new ResponseEntity<GarrafaoDTO>(HttpStatus.BAD_REQUEST);
		}catch (ObjectNotFoundException e) {
			return new ResponseEntity<GarrafaoDTO>(HttpStatus.NOT_FOUND);
		}
	}
}
