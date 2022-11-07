package com.publicissapient.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);

	}

	@Bean
	public OpenAPI openApiDoc() {
		return new OpenAPI().info(new Info().description("Mock prject on Clinic Management")
				.license(new License().name("Free for All")).title("Mock Project Document").version("1.0.0-BETA"));
	}

}