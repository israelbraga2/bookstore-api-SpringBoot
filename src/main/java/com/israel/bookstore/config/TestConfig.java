package com.israel.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.israel.bookstore.service.DBService;

@Configuration

public class TestConfig {
	
	@Autowired
	private DBService dbservice;
	
	@Bean
	public void instanciaBaseDedados() {
		this.dbservice.instanciaBaseDeDados();
	}

}
