package com.daniel.productHex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:env.properties")  // Carga el archivo env.properties
public class ProductHexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductHexApplication.class, args);
	}
}