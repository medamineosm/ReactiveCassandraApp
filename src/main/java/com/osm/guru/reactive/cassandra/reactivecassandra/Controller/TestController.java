package com.osm.guru.reactive.cassandra.reactivecassandra.Controller;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.services.CassandraOperationService;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

@RestController
public class TestController {
    private final CassandraOperationService cassandraOperationService;

    @Autowired
    public TestController(CassandraOperationService cassandraOperationService) {
        this.cassandraOperationService = cassandraOperationService;
    }

    @GetMapping("/")
    public void test(){
        try {
            cassandraOperationService.save(setUp("legrand", "https://www.legrand.fr/", "https://www.legrand.fr/"));
            cassandraOperationService.save(setUp("legrand", "https://www.legrand.fr/", "https://www.legrand.fr/catalogue/interrupteurs-et-prises/interrupteur"));
            cassandraOperationService.save(setUp("legrand", "https://www.legrand.fr/", "https://www.legrand.fr/catalogue/interrupteurs-et-prises/interrupteur/amine"));
            cassandraOperationService.save(setUp("1-2-3", "https://www.1-2-3.com/fr/", "https://www.1-2-3.com/fr/robes/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebPage setUp(String accountId, String website,String url) throws Exception {
        WebPage webPage = new WebPage();
        webPage.setAccountId(accountId);
        webPage.setDateScan(new DateTime().toDate());
        webPage.setWebSite(website);
        webPage.setUrl(url);
        Connection.Response response =  getStatusAndHtml(webPage.getUrl());
        webPage.setHtml(response == null ? null : response.body());
        webPage.setHtmlHash(response == null ? null : BigInteger.valueOf(webPage.getHtml().hashCode()));
        webPage.setInnerLinks(new ArrayList<>());
        webPage.setOrphan(false);
        webPage.setValid(false);
        webPage.setStatus(response == null ? 404 : response.statusCode());
        System.out.println("saving : " +webPage);

        return webPage;
    }

    private Connection.Response getStatusAndHtml(String url){
        Connection.Response response = null;
        try {

            response =Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                    .execute();
        } catch (IOException e) {
            //e.printStackTrace();

        }
        return response;

    }
}
