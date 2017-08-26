package br.com.controleGarrafao.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import br.com.controleGarrafao.DAO.ControleGarrafaoDAO;
import br.com.controleGarrafao.DAO.GarrafaoControle;
import br.com.controleGarrafao.model.Cliente;
import br.com.controleGarrafao.model.ClienteGarrafao;
import br.com.controleGarrafao.model.Garrafao;
import br.com.controleGarrafao.util.HibernateUtil;

public class Teste {

	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
////		
//		session.beginTransaction();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("controleGarrafao");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();    
		ControleGarrafaoDAO controleGarrafaoDAO = new ControleGarrafaoDAO();
		GarrafaoControle garrafaoControle = new GarrafaoControle();
		Cliente cliente = new Cliente();
		List<Cliente> list = new ArrayList<Cliente>();
		Set<ClienteGarrafao> sClienteGarrafaos = new HashSet<ClienteGarrafao>();
//		Cliente cliente = new Cliente();
//		cliente.setClienteNome("Teste");
//		cliente.setClienteRua("Nestor Barbosa");
//		cliente.setClienteNumero(890);
//		
//		Garrafao garrafao = new Garrafao();
//		garrafao.setGarrafaoNome("Cristal");
//		
//		clienteGarrafao.setCliente(cliente);
//		clienteGarrafao.setGarrafao(garrafao);
//		manager.persist(clienteGarrafao);
		Query query = manager.createQuery("SELECT c FROM Cliente c INNER JOIN c.clienteGarrafaos cb ON (c.clienteId = cb.cliente) "
				+ "INNER JOIN Garrafao g ON (g.garrafaoId = cb.garrafao)");
		list = query.getResultList();
		manager.getTransaction().commit();
		manager.close();
		cliente = list.get(0);
		sClienteGarrafaos = cliente.getClienteGarrafaos();
		System.out.println(cliente.getClienteId());
		
		for (ClienteGarrafao clienteGarrafao : sClienteGarrafaos){
			
			System.out.println(clienteGarrafao.getGarrafao().getGarrafaoNome());
		}
//		System.out.println(clienteGarrafao.getGarrafao().getGarrafaoNome());
		
		
		
		
	}

}
