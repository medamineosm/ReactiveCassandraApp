package com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByStatus;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WebPageByStatusRepository extends ReactiveCassandraRepository<WebPageByStatus, WebPageByStatus.Key> {

    @Query("select * FROM webpage_by_status WHERE" +
            " accountid=:#{#key.accountId} " +
            "AND website=:#{#key.webSite} " +
            "AND datescan=:#{#key.dateScan} " +
            "AND status=:#{#key.status}")
    Flux<WebPageByStatus> findAllByStatus(@Param("key") WebPageByStatus.Key key);
}
