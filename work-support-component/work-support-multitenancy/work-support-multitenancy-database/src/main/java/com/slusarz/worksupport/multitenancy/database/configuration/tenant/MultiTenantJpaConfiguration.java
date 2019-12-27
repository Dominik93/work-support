package com.slusarz.worksupport.multitenancy.database.configuration.tenant;

import com.slusarz.worksupport.multitenancy.database.configuration.hibernate.HibernateConfiguration;
import com.slusarz.worksupport.multitenancy.database.extension.PersistencePackageScanProvider;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableJpaRepositories(transactionManagerRef = "txManager")
@EnableTransactionManagement
public class MultiTenantJpaConfiguration {

    @Autowired
    private HibernateConfiguration hibernateConfiguration;

    @Autowired
    private PersistencePackageScanProvider persistencePackageScanProvider;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                           CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
        // No dataSource is set to resulting entityManagerFactoryBean
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPackagesToScan(persistencePackageScanProvider.provide());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaPropertyMap(getHibernateProperties(multiTenantConnectionProvider, currentTenantIdentifierResolver));
        return entityManagerFactoryBean;
    }

    private Map<String, Object> getHibernateProperties(MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                       CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
        Map<String, Object> hibernateProps = new LinkedHashMap<>();
        // hibernateProps.put("hibernate.hbm2ddl.auto", hibernateConfiguration.getDdl());
        hibernateProps.put("hibernate.dialect", hibernateConfiguration.getDialect());
        hibernateProps.put("hibernate.show_sql", hibernateConfiguration.getShowSql().toString());
        hibernateProps.put("hibernate.cache.use_second_level_cache", hibernateConfiguration.getUseSecondLevelCache().toString());
        hibernateProps.put("hibernate.cache.use_query_cache", hibernateConfiguration.getUseQueryCache().toString());

        hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        return hibernateProps;
    }

    @Bean
    public PlatformTransactionManager txManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
