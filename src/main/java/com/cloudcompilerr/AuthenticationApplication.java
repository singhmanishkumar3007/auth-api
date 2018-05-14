package com.cloudcompilerr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}
	
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	

}
