package br.com.sasoriengine.controlegarrafao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBO;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;

@CrossOrigin("*")
@RestController
public class ClienteController {
	
	
	private ClienteGarrafaoBO clienteGarrafaoBO;
	
	@Autowired
	@Qualifier("clienteGarrafaoBO")
	public void setClienteBO(ClienteGarrafaoBO clienteGarrafaoBO) {
		this.clienteGarrafaoBO = clienteGarrafaoBO;
	}
	
	@GetMapping(value = "/cliente/findAll", headers="Accept=application/json", produces = {MediaType.APPLICATION_JSON_VALUE })
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
