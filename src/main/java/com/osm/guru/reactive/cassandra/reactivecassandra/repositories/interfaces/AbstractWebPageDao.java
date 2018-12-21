package com.osm.guru.reactive.cassandra.reactivecassandra.repositories.interfaces;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.services.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

public abstract class AbstractWebPageDao {

    @Autowired private ConverterService converterService;

    //@Async abstract void save(WebPage webPage);
    protected abstract ReactiveCrudRepository getAppropriateRepository();
    protected abstract Class<?> getAppropriateClassType();


    @Async
    public void save(WebPage webpage){
        getAppropriateRepository().save(converterService.toByAppropriatePojo(webpage, getAppropriateClassType())).block();
    }

    //@Async abstract void saveAll(Collection<WebPage> webPages);

}
