package xyz.vegaone.easytrackingv2.config;

import com.zaxxer.hikari.HikariDataSource;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EasyTrackConfig {

    private SpringProperties springProperties;

    @Autowired
    public EasyTrackConfig(SpringProperties springProperties) {
        this.springProperties = springProperties;
    }

    @Bean
    @LiquibaseDataSource
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(springProperties.getDataSource().getJdbcUrl());
        hikariDataSource.setUsername(springProperties.getDataSource().getUsername());
        hikariDataSource.setPassword(springProperties.getDataSource().getPassword());

        return hikariDataSource;
    }

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
}
