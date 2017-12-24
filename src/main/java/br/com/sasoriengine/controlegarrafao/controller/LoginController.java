package br.com.sasoriengine.controlegarrafao.controller;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBO;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoBOImp;
import br.com.sasoriengine.controlegarrafao.dao.ClienteGarrafaoDAOImp;
import br.com.sasoriengine.controlegarrafao.model.Usuario;
import br.com.sasoriengine.controlegarrafao.security.Authority;
import br.com.sasoriengine.controlegarrafao.security.JwtUtils;
import br.com.sasoriengine.controlegarrafao.security.Login;
import br.com.sasoriengine.controlegarrafao.security.User;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager auth;

	public void setAuth(AuthenticationManager auth) {
		this.auth = auth;
	}

	@RequestMapping(path = "")
	public UserDetails login(@RequestBody Login login, HttpServletResponse response) throws JsonProcessingException {

		Authentication credentials = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		
		User user = (User) auth.authenticate(credentials).getPrincipal();
		
		user.setPassword(null);
		response.setHeader("Token", JwtUtils.generateToken(user));
		return user;

	}
}
