package com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Table("webpage_by_datescan")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class WebPageByDateScan extends AbstractWebPage implements QueryImplementation{

    @PrimaryKey
    private WebPageByDateScan.Key key;
    @Column
    private String redirectedUrl;
    @Column
    private int level;
    @Column
    private boolean isValid;
    @Column
    private int status;
    @Column
    private String html;
    @Column
    private BigInteger htmlHash;
    @Column
    private boolean isOrphan;
    @Column
    private List<String> innerLinks;

    @Override
    public WebPage toWebPage() {
        WebPage webPage = this.getWebPageWithAttribute();
        webPage.setStatus(this.status);
        webPage.setRedirectedUrl(this.redirectedUrl);
        webPage.setHtml(this.html);
        webPage.setHtmlHash(this.htmlHash);
        webPage.setInnerLinks(this.innerLinks);
        webPage.setOrphan(this.isOrphan);
        webPage.setValid(this.isValid);
        return webPage;
    }

    @Override
    protected WebPage getWebPageWithAttribute() {
        WebPage webPage = new WebPage();
        webPage.setClientId(this.key.clientId);
        webPage.setAccountId(this.key.accountId);
        webPage.setWebSite(this.key.webSite);
        webPage.setDateScan(this.key.dateScan);
        webPage.setUrl(this.key.url);
        return webPage;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @PrimaryKeyClass
    public static class Key{
        @PrimaryKeyColumn(name = "clientid", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        public int clientId;
        @PrimaryKeyColumn(name = "accountid", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        public String accountId;
        @PrimaryKeyColumn(name = "website", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        public String webSite;
        @PrimaryKeyColumn(name = "datescan", ordinal = 3, type = PrimaryKeyType.PARTITIONED)
        public Date dateScan;
        @PrimaryKeyColumn(name = "url", ordinal = 4, type = PrimaryKeyType.PARTITIONED)
        private String url;
    }

    @Override
    public String toString() {
        return "WebPageByDateScan{" +
                "identityByDateScan=" + key +
                ", redirectedUrl='" + redirectedUrl + '\'' +
                ", level=" + level +
                ", isValid=" + isValid +
                ", status=" + status +
                ", htmlHash=" + htmlHash +
                ", isOrphan=" + isOrphan +
                ", innerLinks=" + innerLinks +
                '}';
    }
}
