package com.linkedin.gms.factory.provider;

import com.linkedin.metadata.configs.ProviderSearchConfig;
import com.linkedin.metadata.dao.search.ESSearchDAO;
import com.linkedin.metadata.search.ProviderDocument;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Nonnull;

@Configuration
public class ProviderSearchDaoFactory {

    @Autowired
    ApplicationContext applicationContext;

    @Nonnull
    @DependsOn({"elasticSearchRestHighLevelClient"})
    @Bean(name = "providerSearchDao")
    protected ESSearchDAO createInstance() {
        return new ESSearchDAO(applicationContext.getBean(RestHighLevelClient.class), ProviderDocument.class,
                new ProviderSearchConfig());
    }
}
