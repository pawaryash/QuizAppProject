package com.project.quizapp.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource(){
        Dotenv dotenv =  Dotenv.load();

        // Create and configure the DataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dotenv.get("DB_URL")); // Load DB URL from .env
        dataSource.setUsername(dotenv.get("DB_USERNAME")); // Load DB username from .env
        dataSource.setPassword(dotenv.get("DB_PASSWORD")); // Load DB password from .env
        dataSource.setDriverClassName("org.postgresql.Driver"); // Set the driver class

        return dataSource;
    }
}
