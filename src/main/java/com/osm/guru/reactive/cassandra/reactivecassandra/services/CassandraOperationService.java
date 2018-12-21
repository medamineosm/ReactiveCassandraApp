package com.osm.guru.reactive.cassandra.reactivecassandra.services;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByStatus;
import com.osm.guru.reactive.cassandra.reactivecassandra.repositories.WebPageByDateScanRepositoryImpl;
import com.osm.guru.reactive.cassandra.reactivecassandra.repositories.WebPageByStatusRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class CassandraOperationService {

    private final WebPageByStatusRepositoryImpl webPageByStatusRepositoryImpl;
    private final WebPageByDateScanRepositoryImpl webPageByDateScanRepositoryImpl;

    @Autowired
    public CassandraOperationService(WebPageByStatusRepositoryImpl webPageByStatusRepositoryImpl, WebPageByDateScanRepositoryImpl webPageByDateScanRepositoryImpl) {
        this.webPageByStatusRepositoryImpl = webPageByStatusRepositoryImpl;
        this.webPageByDateScanRepositoryImpl = webPageByDateScanRepositoryImpl;
    }

    public WebPage save(WebPage webPage){
        log.info("Saving [" +webPage.getUrl()+"]");
        webPageByStatusRepositoryImpl.save(webPage);
        webPageByDateScanRepositoryImpl.save(webPage);
        return webPage;
    }

    public Flux<WebPage> findByStatus(WebPageByStatus.Key key){
        return webPageByStatusRepositoryImpl.findAllWebPageByStatus(key);
    }
}
