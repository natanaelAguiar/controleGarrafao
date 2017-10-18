package br.com.sasoriengine.controlegarrafao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
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
		try {
			cList = (List<Cliente>) session.createQuery("SELECT c FROM Cliente c").list();

			for (Cliente cliente : cList) {

				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO = mapperCLiente(clienteDTO, cliente);
				clientes.add(clienteDTO);
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			session.close();
		}

		return clientes;
	}

	@SuppressWarnings("unchecked")
	public List<GarrafaoDTO> findAllGarrafao() {
		this.session = HibernateUtil.getSessionFactory().openSession();

		List<GarrafaoDTO> garrafaos = new ArrayList<GarrafaoDTO>();
		List<Garrafao> gList = new ArrayList<Garrafao>();

		try {
			gList = (List<Garrafao>) session.createQuery("SELECT g FROM Garrafao g").list();

			for (Garrafao garrafao : gList) {
				GarrafaoDTO garrafaoDTO = new GarrafaoDTO();

				garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);

				garrafaos.add(garrafaoDTO);
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			session.close();
		}
		return garrafaos;
	}

	public ClienteDTO findClienteById(Long id) throws ObjectNotFoundException {
		this.session = HibernateUtil.getSessionFactory().openSession();
		ClienteDTO clienteDTO = new ClienteDTO();
		Cliente cliente = new Cliente();
		try {
			cliente = (Cliente) this.session.load(Cliente.class, id.intValue());

			clienteDTO = mapperCLiente(new ClienteDTO(), cliente);

		} catch (ObjectNotFoundException e) {
			throw e;
		} finally {
			this.session.close();
		}

		return clienteDTO;
	}

	public GarrafaoDTO findGarrafaoById(Long id) throws ObjectNotFoundException {
		this.session = HibernateUtil.getSessionFactory().openSession();
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		Garrafao garrafao = new Garrafao();

		try {
			garrafao = session.load(Garrafao.class, id.intValue());

			garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);
		} catch (ObjectNotFoundException e) {
			throw e;
		} finally {
			this.session.close();
		}

		return garrafaoDTO;
	}

	public ClienteDTO saveOrUpdateCliente(Cliente cliente) throws ValidationException {
		this.session = HibernateUtil.getSessionFactory().openSession();
		ClienteDTO clienteDTO = new ClienteDTO();
		try {
			if (cliente.getClienteId() > 0 && cliente.getClienteGarrafaos().size() > 0) {
				this.session.persist(cliente);
				this.session.flush();
				this.session.getTransaction().commit();
			} else if (cliente.getClienteGarrafaos().size() > 0) {
				clienteDTO = (ClienteDTO) this.session.merge(cliente);
			} else
				throw new ValidationException();

			clienteDTO = mapperCLiente(clienteDTO, cliente);

		} catch (ValidationException e) {
			throw e;
		} finally {
			this.session.close();
		}
		return clienteDTO;
	}

	public GarrafaoDTO saveOrUpdateGarrafao(Garrafao garrafao) throws ValidationException {
		this.session = HibernateUtil.getSessionFactory().openSession();
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		try {
			if (garrafao.getGarrafaoId() > 0) {
				this.session.persist(garrafao);
				this.session.flush();
				this.session.getTransaction().commit();
			} else {
				garrafaoDTO = (GarrafaoDTO) this.session.merge(garrafao);
			}

			garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);
		} catch (ValidationException e) {
			throw e;
		} finally {
			this.session.close();
		}
		return garrafaoDTO;
	}

	public boolean removeClienteById(long id) throws ObjectNotFoundException {
		Cliente cliente = new Cliente();
		this.session = HibernateUtil.getSessionFactory().openSession();
		this.session.beginTransaction();
		try {
			if (id > 0) {
				int rowlsAfected = 0;
				cliente = this.session.load(Cliente.class, id);
				if (cliente.getClienteGarrafaos().size() > 0) {
					String hql = "delete from ClienteGarrafao where CLIENTE_ID = :clienteId";
					Query query = this.session.createQuery(hql);
					query.setLong("clienteId", id);
					rowlsAfected = query.executeUpdate();
					if (rowlsAfected > 0) {
						this.session.delete(cliente);
						this.session.getTransaction().commit();
						return true;
					} else 
						return false;
				} else {
					this.session.delete(cliente);
					this.session.getTransaction().commit();
					return true;
				}
			} else
				return false;
		} catch (ObjectNotFoundException e) {
			this.session.getTransaction().rollback();
			throw e;
		} finally {
			this.session.close();
		}
	}

	@Override
	public boolean removeGarrafaoById(long id) {
		Garrafao garrafao = new Garrafao();
		this.session = HibernateUtil.getSessionFactory().openSession();
		this.session.beginTransaction();
		try {
			if (id > 0) {
				int rowlsAfected = 0;
				garrafao = this.session.load(Garrafao.class, id);
				if (garrafao.getClienteGarrafaos().size() > 0) {
					String hql = "delete from ClienteGarrafao where GARRAFAO_ID = :garrafaoId";
					Query query = this.session.createQuery(hql);
					query.setLong("garrafaoId", id);
					rowlsAfected = query.executeUpdate();
					if (rowlsAfected > 0) {
						this.session.delete(garrafao);
						this.session.getTransaction().commit();
						return true;
					} else
						return false;
				} else {
					this.session.delete(garrafao);
					this.session.getTransaction().commit();
					return true;
				}
			} else
				return false;
		} catch (ObjectNotFoundException e) {
			this.session.getTransaction().rollback();
			throw e;
		} finally {
			this.session.close();
		}
	}

}
