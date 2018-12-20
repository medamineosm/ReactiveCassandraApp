package com.osm.guru.reactive.cassandra.reactivecassandra.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@EnableReactiveCassandraRepositories
@Slf4j
public class EmbeddedCassandraConfig {

    @PostConstruct
    private void initCassandraEmbedded() throws InterruptedException, ConfigurationException, IOException, TTransportException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        final Cluster cluster = Cluster.builder().addContactPoints("localhost").withPort(9142).build();
        log.info("Server Started at localhost");
        final Session session = cluster.connect();
        session.execute("CREATE KEYSPACE local_test WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 3};");
        log.info("KeySpace created and activated.");
        Thread.sleep(5000);
    }
}
