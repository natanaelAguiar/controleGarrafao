package br.com.sasoriengine.controlegarrafao.dao;

import static br.com.sasoriengine.controlegarrafao.util.MapperDTO.mapperCLiente;
import static br.com.sasoriengine.controlegarrafao.util.MapperDTO.mapperGarrafao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sasoriengine.controlegarrafao.exeption.EntityNotFoundException;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;
import br.com.sasoriengine.controlegarrafao.model.Usuario;

@Repository
public class ClienteGarrafaoDAOImp implements ClienteGarrafaoDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ClienteDTO> findAllCliente() {

		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		List<Cliente> cList = new ArrayList<Cliente>();
		try {
			cList = (List<Cliente>) getCurrentSession().createQuery("SELECT c FROM Cliente c").list();

			for (Cliente cliente : cList) {

				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO = mapperCLiente(clienteDTO, cliente);
				clientes.add(clienteDTO);
			}
		} catch (HibernateException e) {
			throw e;
		}

		return clientes;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<GarrafaoDTO> findAllGarrafao() {

		List<GarrafaoDTO> garrafaos = new ArrayList<GarrafaoDTO>();
		List<Garrafao> gList = new ArrayList<Garrafao>();

		try {
			gList = (List<Garrafao>) getCurrentSession().createQuery("SELECT g FROM Garrafao g").list();

			for (Garrafao garrafao : gList) {
				GarrafaoDTO garrafaoDTO = new GarrafaoDTO();

				garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);

				garrafaos.add(garrafaoDTO);
			}

		} catch (HibernateException e) {
			throw e;
		}
		return garrafaos;
	}

	@Override
	@Transactional
	public ClienteDTO findClienteById(Long id) throws ObjectNotFoundException {
		ClienteDTO clienteDTO = new ClienteDTO();
		Cliente cliente = new Cliente();
		try {
			cliente = (Cliente) getCurrentSession().load(Cliente.class, id);
			cliente.getClienteNome();
			clienteDTO = mapperCLiente(new ClienteDTO(), cliente);
			
		}catch (ObjectNotFoundException e) {
			throw e;
		}

		return clienteDTO;
	}

	@Override
	@Transactional
	public GarrafaoDTO findGarrafaoById(Long id) throws ObjectNotFoundException {
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		Garrafao garrafao = new Garrafao();

		try {
			garrafao = getCurrentSession().load(Garrafao.class, id);
			garrafao.getGarrafaoNome();
			garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);
		} catch (ObjectNotFoundException e) {
			throw e;
		}

		return garrafaoDTO;
	}

	@Override
	@Transactional
	public ClienteDTO saveOrUpdateCliente(Cliente cliente) throws ValidationException {
		ClienteDTO clienteDTO = new ClienteDTO();
		try {
			if (cliente.getClienteId() <= 0 && cliente.getClienteGarrafaos().size() > 0) {
				getCurrentSession().persist(cliente);
				getCurrentSession().getTransaction().commit();
			} else if (cliente.getClienteGarrafaos().size() > 0) {
				clienteDTO = (ClienteDTO) getCurrentSession().merge(cliente);
			} else
				throw new ValidationException();

			clienteDTO = mapperCLiente(clienteDTO, cliente);

		} catch (ValidationException e) {
			throw e;
		}
		return clienteDTO;
	}

	@Override
	@Transactional
	public GarrafaoDTO saveOrUpdateGarrafao(Garrafao garrafao) throws ValidationException {
		GarrafaoDTO garrafaoDTO = new GarrafaoDTO();
		try {
			if (garrafao.getGarrafaoId() <= 0) {
				getCurrentSession().persist(garrafao);
				getCurrentSession().flush();
				getCurrentSession().getTransaction().commit();
			} else {
				garrafaoDTO = (GarrafaoDTO) getCurrentSession().merge(garrafao);
			}

			garrafaoDTO = mapperGarrafao(garrafaoDTO, garrafao);
		} catch (ValidationException e) {
			throw e;
		}
		return garrafaoDTO;
	}

	@Override
	@Transactional
	public boolean removeClienteById(long id) throws ObjectNotFoundException {
		Cliente cliente = new Cliente();
		try {
			if (id > 0) {
				int rowlsAfected = 0;
				cliente = getCurrentSession().load(Cliente.class, id);
				if (cliente.getClienteGarrafaos().size() > 0) {
					String hql = "delete from ClienteGarrafao where CLIENTE_ID = :clienteId";
					Query query = getCurrentSession().createQuery(hql);
					query.setLong("clienteId", id);
					rowlsAfected = query.executeUpdate();
					if (rowlsAfected > 0) {
						getCurrentSession().delete(cliente);
						return true;
					} else 
						return false;
				} else {
					getCurrentSession().delete(cliente);
					return true;
				}
			} else
				return false;
		} catch (ObjectNotFoundException e) {
			getCurrentSession().getTransaction().rollback();
			throw e;
		}
	}

	@Override
	@Transactional
	public boolean removeGarrafaoById(long id) {
		Garrafao garrafao = new Garrafao();
		try {
			if (id > 0) {
				int rowlsAfected = 0;
				garrafao = getCurrentSession().load(Garrafao.class, id);
				garrafao.getGarrafaoNome();
				if (garrafao.getClienteGarrafaos().size() > 0) {
					String hql = "delete from ClienteGarrafao where GARRAFAO_ID = :garrafaoId";
					Query query = getCurrentSession().createQuery(hql);
					query.setLong("garrafaoId", id);
					rowlsAfected = query.executeUpdate();
					if (rowlsAfected > 0) {
						getCurrentSession().delete(garrafao);
						return true;
					} else
						return false;
				} else {
					getCurrentSession().delete(garrafao);
					return true;
				}
			} else
				return false;
		} catch (ObjectNotFoundException e) {
			getCurrentSession().getTransaction().rollback();
			throw e;
		}
	}

	@Override
	@Transactional
	public Usuario findUsuarioByUsername(String username) {

		Usuario usuario = new Usuario();
		try {
			if (username != null) {
				String hql = "from Usuario where USUARIO_NOME = :usuarioNome";
				Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
				query.setParameter("usuarioNome", username);
				usuario = (Usuario) query.uniqueResult();
				return usuario;
			} else {
				throw new ValidationException();
			}
		} catch (ObjectNotFoundException e) {
			throw e;
		}
	}

}
