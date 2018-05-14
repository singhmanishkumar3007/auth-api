package com.cloudcompilerr.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudcompilerr.domain.LoginForm;
import com.cloudcompilerr.validator.LoginValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

	String SECRET = "SecretKeyToGenJWTs";
	long EXPIRATION_TIME = 864_000_000; // 10 days
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
	String SIGN_UP_URL = "/users/sign-up";

	@Autowired
	private LoginValidator loginValidator;

	@InitBinder
	public void dataBind(WebDataBinder dataBinder) {
		dataBinder.addValidators(loginValidator);
	}

	@GetMapping(value = "/getName", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getName() {
		return "manish";

	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody @Validated LoginForm login) throws ServletException, JsonProcessingException {

		String token;
		if (login.getUsername().equalsIgnoreCase("manish")) {
			token = Jwts.builder().setSubject(login.getUsername())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

		} else {
			throw new ServletException("Invalid credentials");
		}

		return new ObjectMapper().writeValueAsString(token);

	}

}
