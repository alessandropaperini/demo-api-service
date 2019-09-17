package it.laterale.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * The type Postgres datasource config.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "it.laterale.cloud.repositories",
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager")
public class PostgresDatasourceConfig {

    @Autowired
    private Environment env;

    /**
     * Postgres data source data source.
     *
     * @return the data source
     */
    @Bean
    @Primary
    public DataSource postgresDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(env.getProperty("application.postgres.datasource.url"));
        driverManagerDataSource.setUsername(env.getProperty("application.postgres.datasource.username"));
        driverManagerDataSource.setPassword(env.getProperty("application.postgres.datasource.password"));
        driverManagerDataSource.setDriverClassName(env.getProperty("application.postgres.datasource.driver-class-name"));
        return driverManagerDataSource;
    }

    /**
     * Postgres entity manager local container entity manager factory bean.
     *
     * @return the local container entity manager factory bean
     */
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        LocalContainerEntityManagerFactoryBean postgresEntityManager = new LocalContainerEntityManagerFactoryBean();
        postgresEntityManager.setDataSource(postgresDataSource());
        postgresEntityManager.setPackagesToScan(new String[] {"it.laterale.cloud.entities"});
        HashMap<String, Object> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.ddl-auto", env.getProperty("application.postgres.jpa.hibernate.ddl-auto"));
        hibernateProperties.put("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("application.postgres.jpa.hibernate.jdbc.lob.non_contextual_creation"));
        hibernateProperties.put("hibernate.dialect", env.getProperty("application.postgres.jpa.hibernate.jdbc.dialect"));
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        postgresEntityManager.setJpaVendorAdapter(vendorAdapter);
        postgresEntityManager.setJpaPropertyMap(hibernateProperties);
        return postgresEntityManager;
    }

    /**
     * Postgres transaction manager platform transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    @Primary
    public PlatformTransactionManager postgresTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return jpaTransactionManager;
    }

}
