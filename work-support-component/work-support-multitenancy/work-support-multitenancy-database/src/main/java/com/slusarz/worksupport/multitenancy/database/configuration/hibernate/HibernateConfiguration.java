package com.slusarz.worksupport.multitenancy.database.configuration.hibernate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class HibernateConfiguration {

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private Boolean showSql;

    @Value("${hibernate.hbm2ddl.auto:none}")
    private String ddl;

    @Value("${hibernate.cache.use_second_level_cache}")
    private Boolean useSecondLevelCache;

    @Value("${hibernate.cache.use_query_cache}")
    private Boolean useQueryCache;

}
