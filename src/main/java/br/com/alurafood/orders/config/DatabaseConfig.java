package br.com.alurafood.orders.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
