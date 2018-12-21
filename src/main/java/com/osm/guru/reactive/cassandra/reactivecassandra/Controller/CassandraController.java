package com.osm.guru.reactive.cassandra.reactivecassandra.Controller;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByStatus;
import com.osm.guru.reactive.cassandra.reactivecassandra.services.CassandraOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("find/WebPages/")
public class CassandraController {

    private final CassandraOperationService cassandraReadOperationService;

    @Autowired
    public CassandraController(CassandraOperationService cassandraReadOperationService) {
        this.cassandraReadOperationService = cassandraReadOperationService;
    }

/*
    @GetMapping("findAll")
    public Flux<WebPage> findAll(){
        return cassandraReadOperationService.findAllWebPageByScanDate();
    }
*/

    @PostMapping("getAllByStatus")
    public Flux<WebPage> getAllByStatus(@RequestBody WebPageByStatus.Key keyIdentityByStatus){
        return cassandraReadOperationService.findByStatus(keyIdentityByStatus);
    }
}
