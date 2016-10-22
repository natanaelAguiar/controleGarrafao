package br.com.controleGarrafao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.controleGarrafao.model.*;

public class ControleGarrafaoDAO extends GenericDao<Cliente, Long> {
	
	public ControleGarrafaoDAO(){
		super(Cliente.class);
	}

//	private EntityManagerFactory factory;
//
//	public EntityManager getEm() {
//		this.factory = Persistence.createEntityManagerFactory("controleGarrafao");
//		return factory.createEntityManager();
//	}
//
//	public Garrafao saveOrUpdate(Garrafao contato) {
//		EntityManager entityManager = this.getEm();
//		try {
//			entityManager.getTransaction().begin();
//			contato = entityManager.merge(contato);
//			entityManager.getTransaction().commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			entityManager.getTransaction().rollback();
//		} finally {
//			entityManager.close();
//		}
//		return contato;
//	}
//	
//	public Cliente saveOrUpdate2(Cliente contato) {
//		EntityManager entityManager = this.getEm();
//		try {
//			entityManager.getTransaction().begin();
//			contato = entityManager.merge(contato);
//			entityManager.getTransaction().commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			entityManager.getTransaction().rollback();
//		} finally {
//			entityManager.close();
//		}
//		return contato;
//	}
//	
//	public Cliente findById(Long id) {
//		EntityManager entityManager = this.getEm();
//		Cliente contato;
//		try {
//			contato = entityManager.find(Cliente.class, id);
//		} finally {
//			entityManager.close();
//		}
//		return contato;
//	}

}
