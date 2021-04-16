package com.linkedin.gms.factory.provider;

import com.linkedin.common.urn.ProviderUrn;
import com.linkedin.common.urn.TagUrn;
import com.linkedin.metadata.aspect.ProviderAspect;
import com.linkedin.metadata.aspect.TagAspect;
import com.linkedin.metadata.dao.EbeanLocalDAO;
import com.linkedin.metadata.dao.producer.KafkaMetadataEventProducer;
import com.linkedin.metadata.snapshot.ProviderSnapshot;
import com.linkedin.metadata.snapshot.TagSnapshot;
import io.ebean.config.ServerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Nonnull;

public class ProviderDaoFactory {
    @Autowired
    ApplicationContext applicationContext;

    @Bean(name = "providerDAO")
    @DependsOn({"gmsEbeanServiceConfig", "kafkaEventProducer"})
    @Nonnull
    protected EbeanLocalDAO createInstance() {
        KafkaMetadataEventProducer<ProviderSnapshot, ProviderAspect, ProviderUrn> producer =
                new KafkaMetadataEventProducer(ProviderSnapshot.class, ProviderAspect.class,
                        applicationContext.getBean(Producer.class));
        return new EbeanLocalDAO<>(ProviderAspect.class, producer, applicationContext.getBean(ServerConfig.class),
                ProviderUrn.class);
    }
}
