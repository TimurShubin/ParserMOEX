package com.parsermoex;

import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
