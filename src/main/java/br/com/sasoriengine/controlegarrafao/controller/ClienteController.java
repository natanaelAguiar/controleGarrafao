package br.com.sasoriengine.controlegarrafao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBO;
import br.com.sasoriengine.controlegarrafao.exeption.InvalidRequestException;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;

@CrossOrigin("*")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ClienteController {

	private ClienteGarrafaoBO clienteGarrafaoBO;

	@Autowired
	@Qualifier("clienteGarrafaoBO")
	public void setClienteBO(ClienteGarrafaoBO clienteGarrafaoBO) {
		this.clienteGarrafaoBO = clienteGarrafaoBO;
	}

	@GetMapping(value = "/cliente/findAll", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		return clienteGarrafaoBO.findAllCliente();
	}

	@GetMapping(value = "/cliente/findById/{id}", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> findClienteById(@PathVariable(value = "id") Long id) {
		if(id == 0) {
			throw new InvalidRequestException("Invalid Id", null, "Id nulo ou invalido");
		}
		return clienteGarrafaoBO.findClienteById(id);
	}

	@PutMapping(value = "/cliente/saveOrUpdate", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> saveOrUpdateCliente(@RequestBody @Valid Cliente cliente, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid Cliente", bindingResult, "Verifique se os campos estao preenchidos corretamente");
		}
		return clienteGarrafaoBO.saveOrUpdateCliente(cliente);
	}

	@DeleteMapping(value = "/cliente/removeById/{id}", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> removeById(@PathVariable(value = "id") Long id) {
		return clienteGarrafaoBO.removeClienteById(id);
	}
}
