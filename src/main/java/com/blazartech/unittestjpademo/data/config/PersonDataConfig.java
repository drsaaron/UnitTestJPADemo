/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.unittestjpademo.data.config;

import com.blazartech.products.crypto.BlazarCryptoFile;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author AAR1069
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.blazartech.unittestjpademo.data.repos")
@PropertySource("classpath:person-persistence.properties")
@EnableTransactionManagement
public class PersonDataConfig {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private BlazarCryptoFile cryptoFile;
    
    @Value("${db.resource}")
    private String resourceID;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(cryptoFile.getPassword(env.getProperty("db.user"), resourceID));
 
        return dataSource;
    }
    
    private static final String PERSISTENCE_UNIT_NAME = "com.blazartech_UnitTestJPADemo_jar_0.0.1-SNAPSHOTPU";
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean f = new LocalContainerEntityManagerFactoryBean();
        f.setDataSource(dataSource());
        f.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        f.setJpaVendorAdapter(jpaVendorAdaptor());
        f.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

        // set the hibernate.hbm2ddl.auto property.  Hibernate desperately wants to do
        // schema changes, which is obviously not right.  I've tried setting this
        // property in the persistence.xml file, but hibernate doesn't seem to pick 
        // it up.  So set it here.  And set it to an invalid date so that it doesn't
        // do a thing.
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        f.setJpaProperties(props);

        return f;

    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager m = new JpaTransactionManager(entityManagerFactory().getObject());
        return m;
    }
    
    @Bean(name = "demoJPAVendorAdapter")
    public JpaVendorAdapter jpaVendorAdaptor() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setGenerateDdl(true);
        return va;
    }

}
