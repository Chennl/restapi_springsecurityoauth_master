package com.oak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class JwtAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthorizationServerApplication.class, args);
	}
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/oauth/**").allowedOrigins("http://localhost:9090");
//			}
//		};
//	}
}
