package com.example.multitenant.config;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class MultitenantConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {

        Map<Object, Object> resolvedDataSources = new HashMap<>();

        String tenants = env.getProperty("tenants");
        StringTokenizer tokenizer = new StringTokenizer(tenants, ",");

        while (tokenizer.hasMoreElements()) {
            String tenantId = tokenizer.nextToken().trim();
            HikariConfig config = new HikariConfig();
            HikariDataSource ds;

            config.setJdbcUrl(env.getProperty("spring.datasource." + tenantId + ".url"));
            config.setUsername(env.getProperty("spring.datasource." + tenantId + ".user"));
            config.setPassword(env.getProperty("spring.datasource." + tenantId + ".password"));
            config.addDataSourceProperty("cachePrepStmts" , "true" );
            config.addDataSourceProperty("prepStmtCacheSize" , "250" );
            config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048" );
            ds = new HikariDataSource(config);
            resolvedDataSources.put(tenantId, ds);
        }

        MultitenantDataSource dataSource = new MultitenantDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource());
        dataSource.setTargetDataSources(resolvedDataSources);

        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet();

        return dataSource;
    }

    private DataSource defaultDataSource() {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds = null;
        /*System.out.println("inside default");
        config.setJdbcUrl(env.getProperty("spring.datasource.east.url"));
        config.setUsername(env.getProperty("spring.datasource.east.user"));
        config.setPassword(env.getProperty("spring.datasource.east.password"));
        config.addDataSourceProperty("cachePrepStmts" , "true" );
        config.addDataSourceProperty("prepStmtCacheSize" , "250" );
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);*/
        return ds;
    }
}