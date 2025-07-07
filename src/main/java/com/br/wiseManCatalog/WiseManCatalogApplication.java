package com.br.wiseManCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WiseManCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiseManCatalogApplication.class, args);
	}

}
