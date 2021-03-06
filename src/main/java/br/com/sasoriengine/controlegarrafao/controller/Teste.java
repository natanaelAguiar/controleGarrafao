package br.com.sasoriengine.controlegarrafao.controller;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAO;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAOImp;
import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteGarrafao;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.Usuario;
import br.com.sasoriengine.controlegarrafao.util.HibernateUtil;
import br.com.sasoriengine.controlegarrafao.util.PasswordEncripter;

public class Teste {

	public static void main(String[] args) {
		System.out.println("Hibernate many to many - join table + extra column (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ClienteGarrafaoDAO clienteGarrafaoDAO = new ClienteGarrafaoDAOImp();
		
		Cliente cliente = new Cliente();
//		cliente.setClienteNome("Natanael");
//		cliente.setClienteRua("rua dos bobos");
//		cliente.setClienteNumero(3);
		
		cliente = session.get(Cliente.class, 34L);
		
		Garrafao garrafao = new Garrafao();
//		garrafao.setGarrafaoNome("Pet");
		garrafao = session.get(Garrafao.class, 17L);
//		session.persist(garrafao);
//	    List<Cliente> clientes = new ArrayList<Cliente>();
//		clientes = (List<Cliente>) session.createQuery("SELECT c FROM Cliente c").list();
//		
		ClienteGarrafao clienteGarrafao = new ClienteGarrafao();
//		for(Cliente cliente2 : clientes) {
//			for (ClienteGarrafao clienteGarrafao2 : cliente2.getClienteGarrafaos()) {
//				System.out.println(clienteGarrafao2.getGarrafao().getGarrafaoNome());
//			}
//		}
		clienteGarrafao.setQuantidade(2);
		clienteGarrafao.setCliente(cliente);
		clienteGarrafao.setGarrafao(garrafao);
		
		cliente.getClienteGarrafaos().add(clienteGarrafao);
		System.out.println(cliente.getClienteId());
		System.out.println(cliente.getClienteGarrafaos().size());
//		clienteGarrafaoDAO.saveOrUpdateCliente(cliente);
		session.merge(cliente);
		session.getTransaction().commit();
		session.close();
//		
//		try {
//			String string = PasswordEncripter.encrypt("admin");
//			if (string.equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918"))
//				System.out.println(true);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
	}

}
