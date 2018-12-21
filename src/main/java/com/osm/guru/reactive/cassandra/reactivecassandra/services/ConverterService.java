package com.osm.guru.reactive.cassandra.reactivecassandra.services;

import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.QueryImplementation;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPage;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByDateScan;
import com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages.WebPageByStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConverterService implements ConverterInterface{

    @Override
    public WebPage toWebPage(QueryImplementation webPage) {
        return webPage.toWebPage();
    }

    @Override
    public <T> T toByAppropriatePojo(WebPage webPage, Class<T> clazz) {
        if(clazz.equals(WebPageByDateScan.class)){
            WebPageByDateScan webPageByDateScan = new WebPageByDateScan();
            webPageByDateScan.setKey(new WebPageByDateScan.Key(webPage.getClientId(), webPage.getAccountId(), webPage.getWebSite(), webPage.getDateScan(), webPage.getUrl()));
            webPageByDateScan.setHtml(webPage.getHtml());
            webPageByDateScan.setHtmlHash(webPage.getHtmlHash());
            webPageByDateScan.setInnerLinks(webPage.getInnerLinks());
            webPageByDateScan.setOrphan(webPage.isOrphan());
            webPageByDateScan.setValid(webPage.isValid());
            webPageByDateScan.setStatus(webPage.getStatus());
            return (T) webPageByDateScan;
        }else if(clazz.equals(WebPageByStatus.class)){
            WebPageByStatus webPageByStatus = new WebPageByStatus();
            webPageByStatus.setKey(new WebPageByStatus.Key(webPage.getClientId(), webPage.getAccountId(), webPage.getWebSite(), webPage.getDateScan(), webPage.getStatus()));
            webPageByStatus.setUrl(webPage.getUrl());
            webPageByStatus.setHtml(webPage.getHtml());
            webPageByStatus.setHtmlHash(webPage.getHtmlHash());
            webPageByStatus.setInnerLinks(webPage.getInnerLinks());
            webPageByStatus.setOrphan(webPage.isOrphan());
            webPageByStatus.setValid(webPage.isValid());
            return (T) webPageByStatus;
        }
        return null;
    }
}

interface ConverterInterface {
    WebPage toWebPage(QueryImplementation webPage);
    <T> T toByAppropriatePojo(WebPage webPage, Class<T> clazz);
}