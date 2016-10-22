package br.com.controleGarrafao.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.Valid;

public abstract class GenericDao<T, I extends Serializable> {

	private EntityManagerFactory factory;

	protected EntityManager entityManager = getEm();

	private Class<T> persistedClass;

	protected GenericDao() {
	}

	public EntityManager getEm() {
		this.factory = Persistence.createEntityManagerFactory("controleGarrafao");
		return factory.createEntityManager();
	}

	protected GenericDao(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}

	public T saveOrUpdate(@Valid T t) {
		this.entityManager = getEm();
		EntityTransaction transaction = this.entityManager.getTransaction();
		try {
			transaction.begin();
			t = this.entityManager.merge(t);
			this.entityManager.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.entityManager.getTransaction().rollback();
		}finally {
			this.entityManager.close();
		}
		return t;
	}
	
	public T findById(Long id){
		this.entityManager = getEm();
		EntityTransaction transaction = this.entityManager.getTransaction();
		try {
			transaction.begin();
			return this.entityManager.find(this.persistedClass, id);
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			this.entityManager.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		EntityTransaction transaction = this.entityManager.getTransaction();
		try {
			transaction.begin();
			return this.entityManager.createQuery("FROM"+ this.persistedClass.getName()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}finally {
			this.entityManager.close();
		}	
	}
	
	public boolean removeById(Long id){
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(this.entityManager.find(persistedClass, id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			this.entityManager.getTransaction().rollback();
			return false;
		} finally {
			this.entityManager.close();
		}
		
	}

}
