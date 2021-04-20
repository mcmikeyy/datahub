package com.linkedin.gms.factory.provider;

import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.gms.factory.common.TopicConventionFactory;
import com.linkedin.metadata.aspect.ProviderAspect;
import com.linkedin.metadata.dao.BaseLocalDAO;
import com.linkedin.metadata.dao.EbeanLocalDAO;
import com.linkedin.metadata.dao.producer.KafkaMetadataEventProducer;
import com.linkedin.metadata.snapshot.CorpGroupSnapshot;
import com.linkedin.metadata.snapshot.ProviderSnapshot;
import com.linkedin.mxe.TopicConvention;
import io.ebean.config.ServerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Nonnull;

@Configuration
public class ProviderDaoFactory {

    @Autowired
    ApplicationContext applicationContext;

    @Bean(name = "providerDao")
    @DependsOn({"gmsEbeanServiceConfig", "kafkaEventProducer", TopicConventionFactory.TOPIC_CONVENTION_BEAN})
    @Nonnull
    protected BaseLocalDAO<ProviderAspect, ProviderUrn> createInstance() {
        KafkaMetadataEventProducer<CorpGroupSnapshot, ProviderAspect, ProviderUrn> producer =
                new KafkaMetadataEventProducer(ProviderSnapshot.class, ProviderAspect.class,
                        applicationContext.getBean(Producer.class), applicationContext.getBean(TopicConvention.class));
        return new EbeanLocalDAO<>(ProviderAspect.class, producer, applicationContext.getBean(ServerConfig.class),
               ProviderUrn.class);
    }
}
