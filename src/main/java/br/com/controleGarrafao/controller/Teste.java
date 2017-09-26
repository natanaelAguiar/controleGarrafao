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
import static br.com.controleGarrafao.util.JpaUtil.getEntityManager;
public class Teste {

	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
////		
//		session.beginTransaction();
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();    
		ControleGarrafaoDAO controleGarrafaoDAO = new ControleGarrafaoDAO();
		List<Cliente> list = new ArrayList<Cliente>();
		List<ClienteGarrafao> list1 = new ArrayList<ClienteGarrafao>();
		ClienteGarrafao clienteGarrafao = new ClienteGarrafao();
		
		Cliente cliente = new Cliente();
//		cliente.setClienteNome("Natanael");
//		cliente.setClienteRua("Nestor Barbosa");
//		cliente.setClienteNumero(890);
		cliente = manager.find(Cliente.class, 5L);
		
		Garrafao garrafao = new Garrafao();
//		garrafao.setGarrafaoNome("Pet");
		garrafao = manager.find(Garrafao.class, 3L);
		
		clienteGarrafao.setCliente(cliente);
		clienteGarrafao.setGarrafao(garrafao);
		clienteGarrafao.setQuantidade(3);
//		manager.persist(clienteGarrafao);
//		manager.getTransaction().commit();
//		Query query = manager.createQuery("SELECT c FROM Cliente c INNER JOIN c.clienteGarrafaos cb ON (c.clienteId = cb.cliente) "
//				+ "INNER JOIN Garrafao g ON (g.garrafaoId = cb.garrafao)");
//		list = query.getResultList();
		
//		cliente = list.get(0);
		list = controleGarrafaoDAO.findAllClientes();
//		System.out.println(list.get(0).getClienteNome());
//		System.out.println(list.get(0).getClienteGarrafaos().get(0).getGarrafao().getGarrafaoNome());
		list1 = controleGarrafaoDAO.findAllClienteGarrafao();
//		System.out.println(list1.get(0).getQuantidade());
//		System.out.println(list1.get(0).getCliente().getClienteNome());
//		System.out.println(list1.get(0).getGarrafao().getGarrafaoNome());
		
//		System.out.println(list1.get(1).getQuantidade());
//		System.out.println(list1.get(1).getCliente().getClienteNome());
		for(Cliente cliente2 : list) {
			System.out.println(cliente2.getClienteNome());
			System.out.println(cliente2.getClienteRua());
			for(ClienteGarrafao clienteGarrafao2 : cliente2.getClienteGarrafaos()) {
				System.out.println(clienteGarrafao2.getGarrafao().getGarrafaoNome());
				System.out.println(clienteGarrafao2.getQuantidade());
			}	
		}
	}

}
