package org.highload.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Value("${database.driverClassName}")
    private String driverClass;
    @Value("${database.url}")
    private String url;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("db/${database.initLocation}")
    private String initSqlPath;
    @Value("db/${database.populateDbLocation}")
    private String populateDbLocation;

    @Bean
    public DataSource postgreSqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        initDb(dataSource);
        return dataSource;
    }

    private void initDb(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(initSqlPath));
        populator.addScript(new ClassPathResource(populateDbLocation));
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}
