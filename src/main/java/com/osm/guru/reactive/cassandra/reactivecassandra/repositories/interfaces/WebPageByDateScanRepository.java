package com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByDateScan;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WebPageByDateScanRepository extends ReactiveCassandraRepository<WebPageByDateScan, WebPageByDateScan.Key> {

    @Query("select * FROM webpage_by_datescan WHERE" +
            " accountid=:#{#key.accountId} " +
            "AND website=:#{#key.webSite} " +
            "AND datescan=:#{#key.dateScan}")
    Flux<WebPageByDateScan> findAll(@Param("key") WebPageByDateScan.Key key);
}
