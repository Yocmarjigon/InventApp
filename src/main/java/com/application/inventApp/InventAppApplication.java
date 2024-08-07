package com.application.inventApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
public class InventAppApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(InventAppApplication.class, args);
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

	}

}
