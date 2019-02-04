package com.movedigital;

import com.movedigital.impl.Car;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

//@Configuration
//@ComponentScan(basePackages = {"com.movedigital.impl", "com.movedigital.controller"})
@ComponentScan
@EnableTransactionManagement
public class Conf implements TransactionManagementConfigurer {

//    @Bean
//    public Car voit() {
//        return new Car();
//    }

    @Bean
    public Car voiture() {
        return new Car("Mercedes");
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataSource maDt;


    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName("org.postgresql.Driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl("jdbc:postgresql://localhost:5432/player1");
        dataSource.setUsername("player2");
        dataSource.setPassword("player2");
        return dataSource;
    }

//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setMaximumPoolSize(100);
//        ds.setDataSourceClassName("org.postgresql.Driver");
//        ds.addDataSourceProperty("url", "jdbc:postgresql://localhost:5432/player1");
//        ds.addDataSourceProperty("user", "player2");
//        ds.addDataSourceProperty("password", "player2");
//        ds.addDataSourceProperty("cachePrepStmts", true);
//        ds.addDataSourceProperty("prepStmtCacheSize", 250);
//        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//        ds.addDataSourceProperty("useServerPrepStmts", true);
//        return ds;
//    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        System.out.println(sessionFactory);
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory());
        return transactionManager;
    }

//    @Bean
//    public SessionFactory getSessionFactory() {
//        try {
//            LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
//            lsfb.setDataSource(dataSource());
//            lsfb.setPackagesToScan("com.movedigital.entities");
//            Properties props = new Properties();
//            props.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
//            props.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
//            lsfb.setHibernateProperties(props);
//            lsfb.afterPropertiesSet();
////            lsfb.setConf
//
//            SessionFactory object = lsfb.getObject();
//            return object;
//        } catch (IOException e) {
//            return null;
//        }
//    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.movedigital.entities");
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        props.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
        emf.setJpaProperties(props);
        emf.afterPropertiesSet();
        return emf.getObject();
    }
    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }


}
