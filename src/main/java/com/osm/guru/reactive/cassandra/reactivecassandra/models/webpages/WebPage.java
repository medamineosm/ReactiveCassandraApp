package com.osm.guru.reactive.cassandra.reactivecassandra.models.webpages;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Table("webpage")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebPage {
    @PrimaryKeyColumn(name = "website", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    protected String webSite;
    @Column
    protected Date dateScan;
    @Column
    protected String accountId;
    @Column
    protected String url;
    @Column
    protected String redirectedUrl;
    @Column
    protected int level;
    @Column
    protected boolean isValid;
    @Column
    protected int status;
    @Column
    protected String html;
    @Column
    protected BigInteger htmlHash;
    @Column
    protected boolean isOrphan;
    @Column
    protected String origin;
    @Column
    protected List<String> innerLinks;
    @Override
    public String toString() {
        return "WebPage{" +
                ", webSite='" + webSite + '\'' +
                ", dateScan=" + dateScan +
                ", accountId='" + accountId + '\'' +
                ", url='" + url + '\'' +
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
