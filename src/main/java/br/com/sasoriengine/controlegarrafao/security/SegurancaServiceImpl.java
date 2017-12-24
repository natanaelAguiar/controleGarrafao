package br.com.sasoriengine.controlegarrafao.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBO;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBOImp;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAOImp;
import br.com.sasoriengine.controlegarrafao.model.Usuario;

@Service("segurancaService")
public class SegurancaServiceImpl implements UserDetailsService{

	private ClienteGarrafaoBO clienteGarrafaoBO;
	
	@Autowired
	@Qualifier("clienteGarrafaoBO")
	public void setClienteGarrafaoBO(ClienteGarrafaoBO clienteGarrafaoBO) {
		this.clienteGarrafaoBO = clienteGarrafaoBO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username == null)
			throw new UsernameNotFoundException(username);
		
//		ClienteGarrafaoBO clienteGarrafaoBO = new ClienteGarrafaoBOImp(new ClienteGarrafaoDAOImp());
		Authority authority = new Authority();
		Usuario usuario = new Usuario();
		usuario = clienteGarrafaoBO.findUsuarioByUsername(username);
		System.out.println(usuario.getUsuarioNome());
		
		if(usuario.getUsuarioId() != 0) {
			
			User user = new User();
			user.setUsername(username);
			user.setPassword(usuario.getUsuarioPassword());
			user.setAuthorities(new ArrayList<Authority>());
			authority.setAuthority(usuario.getRole());
			user.getAuthorities().add(authority);
			return user;
		} else
			throw new UsernameNotFoundException("User not found");
		
	}

}
