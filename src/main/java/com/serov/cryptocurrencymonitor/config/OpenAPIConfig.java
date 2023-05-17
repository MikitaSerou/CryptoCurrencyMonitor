package com.serov.cryptocurrencymonitor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("mikita.serou@gmail.com");
        contact.setName("Mikita Serou");

        Info info = new Info()
                .title("CryptoСurrency watcher")
                .version("1.0")
                .contact(contact)
                .description("Test task implementation for IDF Technology. REST service for cryptocurrency monitoring.");

        return new OpenAPI().info(info);
    }
}
