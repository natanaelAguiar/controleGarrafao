package br.com.sasoriengine.controlegarrafao.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAO;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

@CrossOrigin("*")
@RestController
public class controleGarrafaoController {
	
	ClienteGarrafaoDAO clienteGarrafaoDAO = new ClienteGarrafaoDAO();
	
	@RequestMapping(value = "/findAllCliente", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<ClienteDTO> findAllCliente(){
		return clienteGarrafaoDAO.findAllCliente();
	}
	
	@RequestMapping(value = "/findAllGarrafao", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<GarrafaoDTO> findAllGarrafao(){
		return clienteGarrafaoDAO.findAllGarrafao();
	}
	
}
