package com.osm.guru.reactive.cassandra.reactivecassandra.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Table("weppage")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebPage {
    @PrimaryKeyColumn(name = "clientid", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String clientId;
    @PrimaryKeyColumn(name = "website", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String webSite;
    @Column
    private String accountId;
    @Column
    private String url;
    @Column
    private String redirectedUrl;
    @Column
    private int level;
    @Column
    private boolean isValid;
    @Column
    private String parentUrl;
    @Column
    private int status;
    @Column
    private String html;
    @Column
    private int htmlHash;
    @Column
    private boolean isOrphan;
    @Column
    private List<String> innerLinks;
}
