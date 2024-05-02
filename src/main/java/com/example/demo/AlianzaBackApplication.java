package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AlianzaBackApplication extends SpringBootServletInitializer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AlianzaBackApplication.class);

	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AlianzaBackApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AlianzaBackApplication.class, args);
        LOGGER.info("La aplicación se ha iniciado correctamente.");
    }
    
	
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("application");
    }

}
