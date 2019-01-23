package com.osm.guru.reactive.cassandra.reactivecassandra.Controller;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByDateScan;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByStatus;
import com.osm.guru.reactive.cassandra.reactivecassandra.services.CassandraOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("WebPages/")
public class CassandraController {

    private final CassandraOperationService cassandraReadOperationService;

    @Autowired
    public CassandraController(CassandraOperationService cassandraReadOperationService) {
        this.cassandraReadOperationService = cassandraReadOperationService;
    }

    @PostMapping("save")
    public Mono<WebPage> save(@RequestBody WebPage webPage){
        return cassandraReadOperationService.save(webPage);
    }

    @PostMapping("findAll")
    public Flux<WebPage> findAll(@RequestBody WebPageByDateScan.Key key){
        return cassandraReadOperationService.findAll(key);
    }

    @PostMapping("getAllByStatus")
    public Flux<WebPage> getAllByStatus(@RequestBody WebPageByStatus.Key keyIdentityByStatus){
        return cassandraReadOperationService.findByStatus(keyIdentityByStatus);
    }
}
