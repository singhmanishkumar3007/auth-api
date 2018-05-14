package com.cloudcompilerr.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.cloudcompilerr.domain.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements FilterConstants {

	private AuthenticationManager authenticationManager;

	public MyAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		LoginForm loginDetails = null;
		try {
			loginDetails = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Authentication auth=null;
		try {
		auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUsername(),
				loginDetails.getPassword(), new ArrayList<>()));
		
		}
		catch(Exception e)
		{
			System.out.println("exception is "+e);
		}
		return auth;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder().setSubject(((LoginForm) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

	}
}
