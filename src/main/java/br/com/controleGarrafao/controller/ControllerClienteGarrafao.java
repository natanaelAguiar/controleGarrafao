package br.com.controleGarrafao.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleGarrafao.DAO.ControleGarrafaoDAO;
import br.com.controleGarrafao.model.Cliente;
import br.com.controleGarrafao.model.ClienteGarrafao;

@CrossOrigin(origins = "*")
@RestController
public class ControllerClienteGarrafao {

	@RequestMapping(value = "/findAllCliente", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<Cliente> findAllCliente(){
		ControleGarrafaoDAO controleGarrafaoDAO = new ControleGarrafaoDAO();
		return controleGarrafaoDAO.findAllClientes();
	}
	
	@RequestMapping(value = "/findAllClienteGarrafao", headers="Accept=application/json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<ClienteGarrafao> findAllClienteGarrafao(){
		ControleGarrafaoDAO controleGarrafaoDAO = new ControleGarrafaoDAO();
		return controleGarrafaoDAO.findAllClienteGarrafao();
	}
}
