package br.com.sasoriengine.controlegarrafao.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBO;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBOImp;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAO;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAOImp;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

@CrossOrigin("*")
@RestController
public class ClienteController {
	
	ClienteGarrafaoBO clienteGarrafaoBO = new ClienteGarrafaoBOImp(new ClienteGarrafaoDAOImp());
	
	@RequestMapping(value = "/cliente/findAll", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<ClienteDTO>> findAllCliente(){
		return clienteGarrafaoBO.findAllCliente();
	}
	
	@RequestMapping(value = "/cliente/findById/{id}", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> findClienteById(@PathVariable(value = "id") Long id){
		return clienteGarrafaoBO.findClienteById(id);
	}
	
	@RequestMapping(value = "/cliente/saveOrUpdate", headers="Accept=application/json", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> saveOrUpdateCliente(@RequestBody Cliente cliente){
		return clienteGarrafaoBO.saveOrUpdateCliente(cliente);
	}
	
	@RequestMapping(value = "/cliente/removeById/{id}", headers="Accept=application/json", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> removeById(@PathVariable(value = "id") Long id){
		return clienteGarrafaoBO.removeClienteById(id);
	}
}
