package br.com.controleGarrafao.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.controleGarrafao.DAO.ControleGarrafaoDAO;
import br.com.controleGarrafao.DAO.GarrafaoControle;
import br.com.controleGarrafao.model.Cliente;
import br.com.controleGarrafao.model.ClienteGarrafao;
import br.com.controleGarrafao.model.Garrafao;
import br.com.controleGarrafao.util.HibernateUtil;

public class Teste {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		session.beginTransaction();
		
		ControleGarrafaoDAO controleGarrafaoDAO = new ControleGarrafaoDAO();
		GarrafaoControle garrafaoControle = new GarrafaoControle();
		
		Cliente cliente = new Cliente();
		cliente.setClienteNome("Teste");
		cliente.setClienteRua("Nestor Barbosa");
		cliente.setClienteNumero(890);
		
		Garrafao garrafao = new Garrafao();
		garrafao.setGarrafaoNome("Cristal");
		
		garrafaoControle.saveOrUpdate(garrafao);
		
//		session.save(garrafao);
		
		ClienteGarrafao clienteGarrafao = new ClienteGarrafao();
		
		clienteGarrafao.setCliente(cliente);
		clienteGarrafao.setGarrafao(garrafao);
		clienteGarrafao.setQuantidade(3);
		
		cliente.getClienteGarrafaos().add(clienteGarrafao);
		
		controleGarrafaoDAO.saveOrUpdate(cliente);
		
//		session.save(cliente);
		
		Cliente cliente1 = new Cliente();
		
//		cliente1 = (Cliente) session.get(Cliente.class, 1);
		
		System.out.println(cliente1.getClienteGarrafaos().isEmpty());
		
	for(Iterator<ClienteGarrafao> it = cliente1.getClienteGarrafaos().iterator(); it.hasNext();){
		if(it.next().getGarrafao().getGarrafaoNome() == null){
			System.out.println("ei");
		}
	}
		
//		System.out.println(cliente1.getClienteId());
//		System.out.println(cliente1.getClienteNome());
		
//		session.getTransaction().commit();
	}

}
