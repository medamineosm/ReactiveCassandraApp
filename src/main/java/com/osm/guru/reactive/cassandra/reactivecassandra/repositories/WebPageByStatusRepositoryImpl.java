package com.osm.guru.reactive.cassandra.reactivecassandra.repositories;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByStatus;
import com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces.AbstractWebPageDao;
import com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces.WebPageByStatusRepository;
import com.osm.guru.reactive.cassandra.reactivecassandra.services.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service("WebPageByStatusRepositoryImpl")
public class WebPageByStatusRepositoryImpl extends AbstractWebPageDao {

    private final WebPageByStatusRepository webPageByStatusRepository;

    @Autowired
    public WebPageByStatusRepositoryImpl(WebPageByStatusRepository webPageByStatusRepository, ConverterService converterService) {
        this.webPageByStatusRepository = webPageByStatusRepository;
    }

    @Override
    protected ReactiveCrudRepository getAppropriateRepository() {
        return webPageByStatusRepository;
    }

    @Override
    protected Class<?> getAppropriateClassType() {
        return WebPageByStatus.class;
    }

    public Flux<WebPage> findAllWebPageByStatus(WebPageByStatus.Key key){
        return webPageByStatusRepository.findAllByStatus(key).map(WebPageByStatus::toWebPage);
    }

}
