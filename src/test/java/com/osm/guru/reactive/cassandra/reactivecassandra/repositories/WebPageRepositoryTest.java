package com.osm.guru.reactive.cassandra.reactivecassandra.repositories;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.WebPage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WebPageRepositoryTest {
    @Autowired private WebPageRepository webPageRepository;
    private WebPage webPage;

    @Before
    public void setUp() throws Exception {

        this.webPage = new WebPage();
        this.webPage.setAccountId("1-2-3");
        this.webPage.setClientId("0");
        this.webPage.setWebSite("https://www.1-2-3.com/fr/");
        this.webPage.setUrl("https://www.1-2-3.com/fr/robes/");
        Connection.Response response =  getStatusAndHtml(this.webPage.getUrl());
        this.webPage.setParentUrl(null);
        this.webPage.setHtml(response.body());
        this.webPage.setHtmlHash(this.webPage.getHtml().hashCode());
        this.webPage.setInnerLinks(new ArrayList<>());
        this.webPage.setOrphan(false);
        this.webPage.setStatus(response.statusCode());
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
        webPageRepository.save(webPage);
    }
}