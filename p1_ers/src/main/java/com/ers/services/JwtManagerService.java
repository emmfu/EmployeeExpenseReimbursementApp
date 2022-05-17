package com.ers.services;

import java.io.IOException;
import java.security.Key;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ers.models.Manager;
import com.ers.models.ManagerJwtDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;


public class JwtManagerService {
	
	private Key key;
	
	public JwtManagerService() {
		byte[] secret = "my_secret_passwordafdsalkj;lkvjasd;lkfoijeowiru324u02938098134lkhj;ldjfa;sldkjfDSFSLDKJFLSKJF".getBytes();
        key = Keys.hmacShaKeyFor(secret);
	}
	
	public String createJwt(Manager manager) throws InvalidKeyException, JsonProcessingException {
		
		ManagerJwtDTO dto = new ManagerJwtDTO(manager.getManagerId(), manager.getUserName(), manager.getFirstName(), manager.getLastName(), manager.getEmail());
		
		String jwt = Jwts.builder()
				.claim("manager_dto", new ObjectMapper().writeValueAsString(dto))
				.signWith(key)
				.compact();
		
		return jwt;
	}
	
	public ManagerJwtDTO parseJwt(String jwt) throws JsonParseException, JsonMappingException, IOException {
		Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
		
		String dtoString = (String) token.getBody().get("manager_dto");
		
		return (new ObjectMapper()).readValue(dtoString, ManagerJwtDTO.class);
	}
	
	

}
