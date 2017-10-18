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
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAOImp;
import br.com.sasoriengine.controlegarrafao.exeption.ClienteNotFoundException;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

@CrossOrigin("*")
@RestController
public class GarrafaoController {
	
	ClienteGarrafaoBO clienteGarrafaoBO = new ClienteGarrafaoBOImp(new ClienteGarrafaoDAOImp());
	
	@RequestMapping(value = "/garrafao/findAll", headers = "Accept=application/json", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<GarrafaoDTO>> findAllGarrafao() {
		return clienteGarrafaoBO.findAllGarrafao();
	}

	@RequestMapping(value = "/garrafao/findById/{id}", headers = "Accept=application/json", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GarrafaoDTO> findGarrafaoById(@PathVariable(value = "id") Long id) {
		return clienteGarrafaoBO.findGarrafaoById(id);
	}

	@RequestMapping(value = "/garrafao/saveOrUpdate", headers = "Accept=application/json", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GarrafaoDTO> saveOrUpdateGarrafao(@RequestBody Garrafao garrafao) {
		return clienteGarrafaoBO.saveOrUpdateGarrafao(garrafao);
	}
	
	@RequestMapping(value = "/garrafao/removeById/{id}", headers = "Accept=application/json", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<GarrafaoDTO> removeById(@PathVariable long id) throws ClienteNotFoundException{
		return clienteGarrafaoBO.removeGarrafaoById(id);
	}
}
