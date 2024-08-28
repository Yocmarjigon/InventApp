package com.application.inventApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.text.ParseException;


@SpringBootApplication
public class InventAppApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(InventAppApplication.class, args);
	}

}
