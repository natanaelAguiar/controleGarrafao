package br.com.controleGarrafao.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.controleGarrafao.DAO.ControleGarrafaoDAO;
import br.com.controleGarrafao.model.Cliente;
import br.com.controleGarrafao.model.Garrafao;

public class Teste {

	public static void main(String[] args) {
		ControleGarrafaoDAO controleGarrafaoDAO = new ControleGarrafaoDAO();
		Garrafao garrafao1 = new Garrafao();
		garrafao1.setNome("Cristal");
		garrafao1.setId(1L);
//		controleGarrafaoDAO.saveOrUpdate(garrafao);
		Cliente cliente = new Cliente();
		List<Garrafao>garrafao=new ArrayList<Garrafao>();
		garrafao.add(0, garrafao1);
//		
//		cliente.setNome("Sasori");
//		cliente.setNumero(33);
//		cliente.setRua("hehe");
//		cliente.setGarrafao(garrafao);
		
		cliente = controleGarrafaoDAO.findById(2L) ;
//		cliente.setNome("Kappa");
//		cliente.getGarrafao().remove(0);
//		cliente.getGarrafao().add(garrafao1);		
//		cliente = controleGarrafaoDAO.saveOrUpdate(cliente);
//		
//		controleGarrafaoDAO.saveOrUpdate(cliente);
//		controleGarrafaoDAO.saveOrUpdate2(cliente);
		
//		cliente = controleGarrafaoDAO.findById(5L);
		
		System.out.println(cliente.getNome());
		System.out.println(cliente.getGarrafao().get(0).getNome()+"--------------"+cliente.getGarrafao().get(0).getId());

	}

}
