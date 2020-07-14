package server.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import server.properties.DataProps;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.setProperty;

@Configuration
@ComponentScan("server.dao")
@RequiredArgsConstructor
@EnableTransactionManagement
public class DatabaseConfig {
    private final DataProps dataProps;
    private static LocalSessionFactoryBean localSessionFactoryBean;

    @Bean
    public LocalSessionFactoryBean factoryBean() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("server.dao.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        localSessionFactoryBean = sessionFactory;
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataProps.getUrl());
        dataSource.setUsername(dataProps.getUsername());
        dataSource.setPassword(dataProps.getPassword());
        dataSource.setDriverClassName(dataProps.getDriverClassName());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(factoryBean().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    public static SessionFactory getSessionFactory() {
        return localSessionFactoryBean.getObject();
    }
}
