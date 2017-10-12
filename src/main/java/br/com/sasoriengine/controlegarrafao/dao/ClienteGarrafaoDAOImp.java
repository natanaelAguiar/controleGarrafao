package br.com.sasoriengine.controlegarrafao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;
import br.com.sasoriengine.controlegarrafao.util.HibernateUtil;

import static br.com.sasoriengine.controlegarrafao.util.MapperDTO.*;

public class ClienteGarrafaoDAOImp implements ClienteGarrafaoDAO {
	private Session session;

	public ClienteGarrafaoDAOImp() {
	}

	@SuppressWarnings("unchecked")
	public List<ClienteDTO> findAllCliente() {
		this.session = HibernateUtil.getSessionFactory().openSession();

		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		List<Cliente> cList = new ArrayList<Cliente>();

		cList = (List<Cliente>) session.createQuery("SELECT c FROM Cliente c").list();

		for (Cliente cliente : cList) {
			
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO = mapperCLiente(clienteDTO, cliente);
			clientes.add(clienteDTO);
		}
		
		session.close();
		return clientes;
	}

	@SuppressWarnings("unchecked")
	public List<GarrafaoDTO> findAllGarrafao() {
		this.session = HibernateUtil.getSessionFactory().openSession();

		List<GarrafaoDTO> garrafaos = new ArrayList<GarrafaoDTO>();
		List<Garrafao> gList = new ArrayList<Garrafao>();

		gList = (List<Garrafao>) session.createQuery("SELECT g FROM Garrafao g").list();

		for (Garrafao garrafao : gList) {
			GarrafaoDTO garrafaoDTO = new GarrafaoDTO();

			garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);

			garrafaos.add(garrafaoDTO);
		}
		session.close();
		return garrafaos;
	}

	public ClienteDTO findClienteById(Long id) throws ObjectNotFoundException {
		this.session = HibernateUtil.getSessionFactory().openSession();
		ClienteDTO clienteDTO = new ClienteDTO();
		Cliente cliente = new Cliente();

		cliente = (Cliente) this.session.load(Cliente.class, id.intValue());

		clienteDTO = mapperCLiente(new ClienteDTO(), cliente);

		this.session.close();

		return clienteDTO;
	}

	public GarrafaoDTO findGarrafaoById(Long id) throws ObjectNotFoundException{
		this.session = HibernateUtil.getSessionFactory().openSession();
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		Garrafao garrafao = new Garrafao();

		garrafao = session.load(Garrafao.class, id.intValue());

		garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);
		
		this.session.close();
		return garrafaoDTO;

	}

	public ClienteDTO saveOrUpdateCliente(Cliente cliente) throws ValidationException{
		this.session = HibernateUtil.getSessionFactory().openSession();
		
		ClienteDTO clienteDTO = new ClienteDTO();
		if(cliente.getClienteId() > 0) {			
			this.session.persist(cliente);
			this.session.flush();
			this.session.getTransaction().commit();
		}else {
			clienteDTO = (ClienteDTO) this.session.merge(cliente);
		}
		
		clienteDTO = mapperCLiente(clienteDTO, cliente);
		
		this.session.close();
		return clienteDTO;
	}

	public GarrafaoDTO saveOrUpdateGarrafao(GarrafaoDTO garrafaoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
