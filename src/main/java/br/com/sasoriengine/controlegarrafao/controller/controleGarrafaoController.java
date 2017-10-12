package br.com.sasoriengine.controlegarrafao.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBO;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBOImp;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAO;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAOImp;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

@CrossOrigin("*")
@RestController
public class controleGarrafaoController {
	
	ClienteGarrafaoBO clienteGarrafaoBO = new ClienteGarrafaoBOImp(new ClienteGarrafaoDAOImp());
	
	@RequestMapping(value = "/findAllCliente", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<ClienteDTO> findAllCliente(){
		return clienteGarrafaoBO.findAllCliente();
	}
	
	@RequestMapping(value = "/findAllGarrafao", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<GarrafaoDTO> findAllGarrafao(){
		return clienteGarrafaoBO.findAllGarrafao();
	}
	
	@RequestMapping(value = "/findClienteById/{id}", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> findClienteById(@PathVariable(value = "id") Long id){
		return clienteGarrafaoBO.findClienteById(id);
	}
	
	@RequestMapping(value = "/findGarrafaoById/{id}", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GarrafaoDTO> findGarrafaoById(@PathVariable(value = "id") Long id){
		return clienteGarrafaoBO.findGarrafaoById(id);
	}
	@RequestMapping(value = "/saveOrUpdateCliente", headers="Accept=application/json", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteDTO> saveOrUpdateCliente(@RequestBody Cliente cliente){
		return clienteGarrafaoBO.saveOrUpdateCliente(cliente);
	}
}
