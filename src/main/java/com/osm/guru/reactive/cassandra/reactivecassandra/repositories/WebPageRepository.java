package com.osm.guru.reactive.cassandra.reactivecassandra.repositories;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.WebPage;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebPageRepository extends ReactiveCassandraRepository<WebPage, String> {
}
