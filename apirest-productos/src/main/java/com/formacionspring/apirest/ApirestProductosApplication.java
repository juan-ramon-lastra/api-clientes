package com.formacionspring.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApirestProductosApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ApirestProductosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String pass = "hola1";
		for (int i = 0; i < 3; i++) {
			String passBCrypt = passwordEncoder.encode(pass);
			System.out.println(passBCrypt);
		}
	}

}
