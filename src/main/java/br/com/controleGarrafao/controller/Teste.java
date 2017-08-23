package br.com.controleGarrafao.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		ClienteGarrafao clienteGarrafao = new ClienteGarrafao();
		List<ClienteGarrafao> list = new ArrayList<ClienteGarrafao>();
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
		Query query = manager.createQuery("SELECT clientes_garrafaos INNER JOIN clientes on clientes.cliente_id = clientes_garrafaos.cliente_id"+ 
"JOIN FETCH garrafaos on garrafaos.garrafao_id = clientes_garrafaos.garrafao_id ");
		list = query.getResultList();
		manager.getTransaction().commit();
		manager.close();
		clienteGarrafao = list.get(0);
		System.out.println(clienteGarrafao.getCliente().getClienteNome());
		System.out.println(clienteGarrafao.getGarrafao().getGarrafaoNome());
		
		
		
		
	}

}
