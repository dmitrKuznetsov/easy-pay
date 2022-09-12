package com.github.dmitrkuznetsov.backend.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.github.dmitrkuznetsov.backend")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class MyConfig {

    @Setter
    @Value("${spring.datasource.url}")
    private String databaseJdbcUrl;
    @Setter
    @Value("${spring.datasource.username}")
    private String databaseUser;
    @Setter
    @Value("${spring.datasource.password}")
    private String databasePassword;
    @Setter
    @Value("${spring.datasource.driver-class-name}")
    private String databaseDriverClass;

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setJdbcUrl(databaseJdbcUrl);
            dataSource.setUser(databaseUser);
            dataSource.setPassword(databasePassword);
            dataSource.setDriverClass(databaseDriverClass);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    @Bean
    public Flyway flyway() {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource(
                databaseJdbcUrl,
                databaseUser,
                databasePassword
        ).load();

        // Start the migration
        flyway.migrate();

        return flyway;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.github.dmitrKuznetsov.rest.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.MySQLDialect"
        );
        hibernateProperties.setProperty(
                "hibernate.show_sql", "true"
        );

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

}
