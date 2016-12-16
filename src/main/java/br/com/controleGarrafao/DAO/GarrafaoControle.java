package br.com.controleGarrafao.DAO;

import br.com.controleGarrafao.model.Cliente;
import br.com.controleGarrafao.model.Garrafao;

public class GarrafaoControle extends GenericDao<Garrafao, Long> {
	
	public GarrafaoControle() {
		super(Garrafao.class);
	}

}
