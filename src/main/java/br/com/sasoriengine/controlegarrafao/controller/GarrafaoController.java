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
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

@CrossOrigin("*")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class GarrafaoController {

	private ClienteGarrafaoBO clienteGarrafaoBO;

	@Autowired
	@Qualifier("clienteGarrafaoBO")
	public void setClienteGarrafaoBO(ClienteGarrafaoBO clienteGarrafaoBO) {
		this.clienteGarrafaoBO = clienteGarrafaoBO;
	}

	@GetMapping(value = "/garrafao/findAll", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<GarrafaoDTO>> findAllGarrafao() {
		return clienteGarrafaoBO.findAllGarrafao();
	}

	@GetMapping(value = "/garrafao/findById/{id}", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GarrafaoDTO> findGarrafaoById(@PathVariable(value = "id") Long id) {
		if(id == 0) {
			throw new InvalidRequestException("Invalid Id", null, "Id nulo ou invalido");
		}
		return clienteGarrafaoBO.findGarrafaoById(id);
	}

	@PutMapping(value = "/garrafao/saveOrUpdate", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GarrafaoDTO> saveOrUpdateGarrafao(@RequestBody @Valid Garrafao garrafao, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid Cliente", bindingResult, "Verifique se os campos estao preenchidos corretamente");
		}
		return clienteGarrafaoBO.saveOrUpdateGarrafao(garrafao);
	}

	@DeleteMapping(value = "/garrafao/removeById/{id}", headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GarrafaoDTO> removeById(@PathVariable long id) {
		return clienteGarrafaoBO.removeGarrafaoById(id);
	}
}
