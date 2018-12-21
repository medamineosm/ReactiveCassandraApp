package com.osm.guru.reactive.cassandra.reactivecassandra.services;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CassandraOperationServiceTest {
    @Autowired private CassandraOperationService cassandraReadOperationService;
    final String testDataEtam = "TestData/Etam.csv";
    final String testData123 = "TestData/123.csv";

    @Before
    public void setUp() throws Exception {
        FileUtils
                //.readLines(new File(testDataEtam), "UTF-8")
                .readLines(new File(testData123), "UTF-8")
                .parallelStream()
                //.map(url -> buidlWebPage(0, "1-2-3", "https://www.etam.com/", url, new DateTime(2018, 12, 21, 0, 0).toDate()))
                .map(url -> buidlWebPage(0, "1-2-3", "https://www.1-2-3.com/fr/", url, new DateTime(2018, 12, 21, 0, 0).toDate()))
                .forEach(webPage -> cassandraReadOperationService.save(webPage));

    }

    private WebPage buidlWebPage(int clientId, String accountId, String website, String url, Date dateScan){
        WebPage webPage = new WebPage();
        webPage.setAccountId(accountId);
        webPage.setDateScan(dateScan);
        webPage.setClientId(clientId);
        webPage.setWebSite(website);
        webPage.setUrl(url);
        Connection.Response response =  getStatusAndHtml(webPage.getUrl());
        webPage.setHtml(response == null ? null : response.body());
        webPage.setHtmlHash(response == null ? null : BigInteger.valueOf(webPage.getHtml().hashCode()));
        webPage.setStatus(response == null ? 404 : response.statusCode());
        webPage.setInnerLinks(new ArrayList<>());
        webPage.setOrphan(false);
        webPage.setValid(false);
        return webPage;
    }

    private Connection.Response getStatusAndHtml(String url){
        Connection.Response response = null;
        try {
            response = Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
                    .execute();
        } catch (IOException e) {
            log.error(e.getMessage());
            //e.printStackTrace();
        }

        return response;
    }

    @Test
    public void findAllWebPageByScanDate() {
    }

    @Ignore
    @Test
    public void findOneByScanDateAndUrl() {
    }

    @Ignore
    @Test
    public void findAllWebPageByStatus() {
    }
}