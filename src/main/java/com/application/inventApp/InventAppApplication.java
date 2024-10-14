package com.application.inventApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;





@SpringBootApplication
public class InventAppApplication {

	public static void main(String[] args){
		SpringApplication.run(InventAppApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
		
	}

}
