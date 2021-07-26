package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii")
public class SpringJdbcConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/gestiunea_documentelor");
        dataSource.setUsername("root");
        dataSource.setPassword("password");

        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
       return new JdbcTemplate(dataSource());
    }
}