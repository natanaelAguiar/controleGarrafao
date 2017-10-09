package br.com.sasoriengine.controlegarrafao.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.ClienteGarrafao;
import br.com.sasoriengine.controlegarrafao.model.ClienteGarrafaoDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;
import br.com.sasoriengine.controlegarrafao.util.HibernateUtil;
import static br.com.sasoriengine.controlegarrafao.util.MapperDTO.*;

public class ClienteGarrafaoDAO {
	private Session session;
	
	public ClienteGarrafaoDAO() {
	}
	
	@SuppressWarnings("unchecked")
	public List<ClienteDTO> findAllCliente(){
		this.session = HibernateUtil.getSessionFactory().openSession();
		
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		List<Cliente> cList = new ArrayList<Cliente>();
		
		cList = (List<Cliente>) session.createQuery("SELECT c FROM Cliente c").list();
		
		for(Cliente cliente : cList) {
			ClienteDTO clienteDTO = new ClienteDTO();
			
			clienteDTO = mapperCLiente(clienteDTO, cliente);
			
			for(ClienteGarrafao clienteGarrafao : cliente.getClienteGarrafaos()) {
				ClienteGarrafaoDTO clienteGarrafaoDTO = new ClienteGarrafaoDTO();
				
				clienteGarrafaoDTO = mapperClienteForGarrafao(clienteGarrafaoDTO, clienteGarrafao);
				
				clienteDTO.getClienteGarrafaos().add(clienteGarrafaoDTO);
			}
			
			clientes.add(clienteDTO);
			
		}
		session.close();
		return clientes;
	}
	
	@SuppressWarnings("unchecked")
	public List<GarrafaoDTO> findAllGarrafao(){
		this.session = HibernateUtil.getSessionFactory().openSession();
		
		List<GarrafaoDTO> garrafaos = new ArrayList<GarrafaoDTO>();
		List<Garrafao> gList = new ArrayList<Garrafao>();
		
		gList = (List<Garrafao>) session.createQuery("SELECT g FROM Garrafao g").list();
		
		for(Garrafao garrafao : gList) {
			GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
			
			garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);
			
			for(ClienteGarrafao clienteGarrafao : garrafao.getClienteGarrafaos()) {
				ClienteGarrafaoDTO clienteGarrafaoDTO = new ClienteGarrafaoDTO();
				
				clienteGarrafaoDTO = mapperGarrafaoForCliente(clienteGarrafaoDTO, clienteGarrafao);
				
				garrafaoDTO.getClienteGarrafaos().add(clienteGarrafaoDTO);
			}
			garrafaos.add(garrafaoDTO);
		}
		session.close();
		return garrafaos;
	}
	
}
