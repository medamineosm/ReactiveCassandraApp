package com.osm.guru.reactive.cassandra.reactivecassandra.repositories;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByDateScan;
import com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces.AbstractWebPageDao;
import com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces.WebPageByDateScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service("WebPageByDateScanRepositoryImpl")
public class WebPageByDateScanRepositoryImpl extends AbstractWebPageDao {

    private final WebPageByDateScanRepository webPageByDateScanRepository;

    @Autowired
    public WebPageByDateScanRepositoryImpl(WebPageByDateScanRepository webPageByDateScanRepository) {
        this.webPageByDateScanRepository = webPageByDateScanRepository;
    }

    @Override
    protected ReactiveCrudRepository getAppropriateRepository() {
        return webPageByDateScanRepository;
    }

    @Override
    protected Class<?> getAppropriateClassType() {
        return WebPageByDateScan.class;
    }

    public Flux<WebPage> findAllByScanDate(WebPageByDateScan.Key key){
        return webPageByDateScanRepository.findAll(key).map(WebPageByDateScan::toWebPage);
    }

    public Mono<WebPage> findOneByScanDateAndUrl(WebPageByDateScan.Key key){
        return webPageByDateScanRepository.findById(key).map(WebPageByDateScan::toWebPage);
    }

}
