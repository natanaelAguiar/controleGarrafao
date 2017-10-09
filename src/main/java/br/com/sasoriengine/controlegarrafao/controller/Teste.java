package br.com.sasoriengine.controlegarrafao.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteGarrafao;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.util.HibernateUtil;

public class Teste {

	public static void main(String[] args) {
		System.out.println("Hibernate many to many - join table + extra column (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Cliente cliente = new Cliente();
//		cliente.setClienteNome("Natanael");
//		cliente.setClienteRua("rua dos bobos");
//		cliente.setClienteNumero(3);
		
		cliente = session.get(Cliente.class, 16);
		
		Garrafao garrafao = new Garrafao();
		garrafao.setGarrafaoNome("Pet");
//		garrafao = session.get(Garrafao.class, 16);
		session.persist(garrafao);
	    List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = (List<Cliente>) session.createQuery("SELECT c FROM Cliente c").list();
//		
		ClienteGarrafao clienteGarrafao = new ClienteGarrafao();
		for(Cliente cliente2 : clientes) {
			for (ClienteGarrafao clienteGarrafao2 : cliente2.getClienteGarrafaos()) {
				System.out.println(clienteGarrafao2.getGarrafao().getGarrafaoNome());
			}
		}
		clienteGarrafao.setQuantidade(2);
		clienteGarrafao.setCliente(cliente);
		clienteGarrafao.setGarrafao(garrafao);
		
		cliente.getClienteGarrafaos().add(clienteGarrafao);
//		
//		session.merge(cliente);
//		session.getTransaction().commit();
//		
	}

}
