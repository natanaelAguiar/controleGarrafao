package br.com.sasoriengine.controlegarrafao.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
	private static final String secretKey = "c0tr0l3_g@rr@f@0";

	public static String generateToken(User user) throws JsonProcessingException {

		final Long hora = 1000L * 60L * 60L;

		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		Date agora = new Date();
		return Jwts.builder().claim("usr", userJson).setIssuer("br.com.sasoriengine").setSubject(user.getUsername())
				.setExpiration(new Date(agora.getTime() + hora)).signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public static UserDetails parseToken(String token) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String userJson = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("usr",
				String.class);

		return mapper.readValue(userJson, User.class);
	}
}
