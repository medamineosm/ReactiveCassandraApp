package com.osm.guru.reactive.cassandra.reactivecassandra.repositories;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.services.CassandraOperationService;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WebPageRepositoryTest {
    @Autowired private CassandraOperationService cassandraOperationService;
    private WebPage webPage = new WebPage();

    @Before
    public void setUp() throws Exception {
        webPage.setAccountId("legrand");
        webPage.setDateScan( new DateTime(2018, 12, 20, 0, 0).toDate());
        webPage.setClientId(0);
        webPage.setWebSite("https://www.1-2-3.com/fr/");
        webPage.setUrl("https://www.1-2-3.com/fr/tailleurs/");
        Connection.Response response =  getStatusAndHtml(webPage.getUrl());
        webPage.setHtml(response.body());
        webPage.setHtmlHash(BigInteger.valueOf(webPage.getHtml().hashCode()));
        webPage.setInnerLinks(new ArrayList<>());
        webPage.setOrphan(false);
        webPage.setValid(false);
        webPage.setStatus(404);
        System.out.println("saving : " +webPage);
    }

    private Connection.Response getStatusAndHtml(String url){
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Test
    public void saveTest(){
        cassandraOperationService.save(webPage);
    }
}