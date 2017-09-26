package br.com.controleGarrafao.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import static br.com.controleGarrafao.util.JpaUtil.getEntityManager;
import static br.com.controleGarrafao.util.JpaUtil.closeEntityManager;

import br.com.controleGarrafao.model.Cliente;
import br.com.controleGarrafao.model.ClienteGarrafao;

public class ControleGarrafaoDAO {
	
	
	public List<Cliente> findAllClientes(){
		EntityManager manager = getEntityManager();
//		Query query = manager.createQuery("SELECT c FROM Cliente c INNER JOIN c.clienteGarrafaos cb ON (c.clienteId = cb.cliente) "
//				+ "INNER JOIN Garrafao g ON (g.garrafaoId = cb.garrafao)");
//		closeEntityManager();
//		return (List<Cliente>) query.getResultList();
		return (List<Cliente>) manager.createQuery("FROM " + Cliente.class.getName()).getResultList();
	}
	
	public List<ClienteGarrafao> findAllClienteGarrafao() {
		EntityManager manager = getEntityManager();
		Query query = manager.createQuery("SELECT cg FROM ClienteGarrafao cg INNER JOIN cg.cliente c ON (c.clienteId = cg.cliente) "
				+ "INNER JOIN Garrafao g ON (g.garrafaoId = cg.garrafao)");
		return (List<ClienteGarrafao>) query.getResultList();
	}
		
}
