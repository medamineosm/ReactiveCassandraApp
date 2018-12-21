package com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByDateScan;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebPageByDateScanRepository extends ReactiveCassandraRepository<WebPageByDateScan, WebPageByDateScan.Key> {
}
