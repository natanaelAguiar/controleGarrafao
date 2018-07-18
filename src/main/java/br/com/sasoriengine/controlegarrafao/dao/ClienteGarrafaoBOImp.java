package br.com.sasoriengine.controlegarrafao.dao;

import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import br.com.sasoriengine.controlegarrafao.exeption.EntityNotFoundException;
import br.com.sasoriengine.controlegarrafao.exeption.InvalidRequestException;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;
import br.com.sasoriengine.controlegarrafao.model.Usuario;

@Service
public class ClienteGarrafaoBOImp implements ClienteGarrafaoBO {

	private ClienteGarrafaoDAO clienteGarrafaoDAO;

	public void setClienteGarrafaoDAO(ClienteGarrafaoDAO clienteGarrafaoDAO) {
		this.clienteGarrafaoDAO = clienteGarrafaoDAO;
	}

	public ClienteGarrafaoBOImp() {

	}

	@Override
	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		try {
			return new ResponseEntity<List<ClienteDTO>>(this.clienteGarrafaoDAO.findAllCliente(), HttpStatus.OK);
		} catch (HibernateException e) {
			return new ResponseEntity<List<ClienteDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<GarrafaoDTO>> findAllGarrafao() {
		try {

			return new ResponseEntity<List<GarrafaoDTO>>(clienteGarrafaoDAO.findAllGarrafao(), HttpStatus.OK);
		} catch (HibernateException e) {
			return new ResponseEntity<List<GarrafaoDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ClienteDTO> findClienteById(Long id) {
		ClienteDTO clienteDTO = new ClienteDTO();
		if (id > 0) {
			try {
				clienteDTO = clienteGarrafaoDAO.findClienteById(id);
			} catch (ObjectNotFoundException e) {
				throw new EntityNotFoundException("Cliente Not Found", null, "Cliente nao encontrado");
			}
			return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<ClienteDTO>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	public ResponseEntity<GarrafaoDTO> findGarrafaoById(Long id) {
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		if (id > 0) {
			try {
				garrafaoDTO = clienteGarrafaoDAO.findGarrafaoById(id);
			} catch (ObjectNotFoundException e) {
				throw new EntityNotFoundException("Cliente Not Found", null, "Cliente nao encontrado");
			}
			return new ResponseEntity<GarrafaoDTO>(garrafaoDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<GarrafaoDTO>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ClienteDTO> saveOrUpdateCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		try {
			if (cliente.getClienteNome() != null && cliente.getClienteGarrafaos().size() > 0
					&& cliente.getClienteNumero() > 0 && cliente.getClienteRua() != null) {
				clienteDTO = clienteGarrafaoDAO.saveOrUpdateCliente(cliente);
				return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
			} else
				throw new InvalidRequestException("Invalid Entity", null,
						"Verifique se os campos estao preenchidos corretamente");

		} catch (ValidationException e) {
			throw new InvalidRequestException("Invalid Entity", null,
					"Verifique se os campos estao preenchidos corretamente");
		}
	}

	@Override
	public ResponseEntity<GarrafaoDTO> saveOrUpdateGarrafao(Garrafao garrafao) {
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		try {
			if (garrafao.getGarrafaoNome() != null) {
				garrafaoDTO = clienteGarrafaoDAO.saveOrUpdateGarrafao(garrafao);
				return new ResponseEntity<GarrafaoDTO>(garrafaoDTO, HttpStatus.OK);
			} else
				throw new InvalidRequestException("Invalid Entity", null,
						"Verifique se os campos estao preenchidos corretamente");

		} catch (ValidationException e) {
			throw new InvalidRequestException("Invalid Entity", null,
					"Verifique se os campos estao preenchidos corretamente");
		}
	}

	@Override
	public ResponseEntity<ClienteDTO> removeClienteById(long id) {
		try {
			if (id > 0) {
				if (clienteGarrafaoDAO.removeClienteById(id))
					return new ResponseEntity<ClienteDTO>(HttpStatus.OK);
				else
					return new ResponseEntity<ClienteDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else
				return new ResponseEntity<ClienteDTO>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new EntityNotFoundException("Check if Cliente exists in database", null, "Cliente nao Encontrado");
		}
	}

	@Override
	public ResponseEntity<GarrafaoDTO> removeGarrafaoById(long id) {
		try {
			if (id > 0) {
				if (clienteGarrafaoDAO.removeGarrafaoById(id))
					return new ResponseEntity<GarrafaoDTO>(HttpStatus.OK);
				else
					return new ResponseEntity<GarrafaoDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			} else
				return new ResponseEntity<GarrafaoDTO>(HttpStatus.BAD_REQUEST);
		} catch (ObjectNotFoundException e) {
			throw new EntityNotFoundException("Check if Garrafao exists in database", null, "Garrafao nao Encontrado");

		}
	}

	@Override
	public Usuario findUsuarioByUsername(String username) {
		if (username != null)
			try {
				return clienteGarrafaoDAO.findUsuarioByUsername(username);

			} catch (ValidationException vE) {
				throw vE;
			}
		else
			throw new ValidationException();
	}
}
