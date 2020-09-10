package com.homework.Feed.Training.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DB2Config {
    @Value("${db2.datasource.url}")
    private String url;
    @Value("${db2.datasource.username}")
    private String username;
    @Value("${db2.datasource.password")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.connection-test-query}")
    private String connectionTestQuery;

    @Bean(name="db2DataSource")
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClass);
        ds.setConnectionTestQuery(connectionTestQuery);
        ds.setAutoCommit(Boolean.FALSE);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
