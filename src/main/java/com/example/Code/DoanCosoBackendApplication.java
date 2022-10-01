package com.example.Code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2
public class DoanCosoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoanCosoBackendApplication.class, args);
	}

}
