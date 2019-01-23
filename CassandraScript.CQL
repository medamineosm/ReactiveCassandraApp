CREATE keyspace test WITH REPLICATION = {'class' : 'SimpleStrategy','replication_factor': 3 };
DROP TABLE IF EXISTS TABLE test.webpage_by_datescan; CREATE TABLE test.webpage_by_datescan (
  website text,
  accountid text,
  datescan timestamp,
  url text,
  status int,
  redirectedurl text,
  level int,
  isvalid boolean,
  isorphan boolean,
  innerLinks list<text>,
  htmlhash varint,
  html text,
  PRIMARY KEY ((accountid, website), datescan, url)
);

DROP TABLE IF EXISTS test.webpage_by_status; CREATE TABLE test.webpage_by_status (
website text,
accountid text,
datescan timestamp,
url text,
status int,
redirectedurl text,
level int,
isvalid boolean,
isorphan boolean,
innerLinks list<text>,
htmlhash varint,
html text,
PRIMARY KEY ((accountid, website), datescan, status, url)
);

SELECT website,accountid,htmlhash,innerlinks,isorphan,isvalid,level,redirectedurl,status,url,datescan FROM  webpage_by_datescan;
SELECT website,accountid,htmlhash,innerlinks,isorphan,isvalid,level,redirectedurl,status,url,datescan FROM  webpage_by_statussele;
select url from webpage_by_status WHERE website='https://www.legrand.fr/' AND  accountid='legrand' AND datescan='2018-12-19 23:00:00' AND status=200;
select url from webpage_by_status WHERE website='http://www.1-2-3.com/fr/' AND  accountid='1-2-3' AND datescan='2018-12-20 23:00:00' AND status=200;

select url, accountid, status from webpage_by_status  where accountid='1-2-3' AND website='https://www.1-2-3.com/fr/' AND datescan='2018-12-20 23:00:00-0000' AND status=200 limit 10;
select url, status from webpage_by_status WHERE website='https://www.1-2-3.com/fr/' AND  accountid='1-2-3' and datescan='2018-12-20 23:00:00-0000' and status=404;

CREATE INDEX index_isvalid ON test.webpage_by_datescan (isvalid);

select count(*) from webpage_by_status WHERE website='https://www.legrand.fr/'  AND accountid='legrand' AND datescan='2018-12-19 23:00:00-0000' AND status=200;
select count(*) from webpage_by_status WHERE website='https://www.etam.com/'  AND accountid='1-2-3' AND datescan='2018-12-20 23:00:00-0000';


DROP TABLE IF EXISTS test.webpage_resource; CREATE TABLE test.webpage_resource (
website text,
accountid text,
datescan timestamp,
url text,
status int,
redirectedurl text,
level int,
isvalid boolean,
type, text
PRIMARY KEY ((accountid, website), datescan, url)
);

ALTER TABLE test.webpage_by_datescan ADD origin TEXT;
ALTER TABLE test.webpage_by_status ADD origin TEXT;
CREATE INDEX index_origin ON test.webpage_by_datescan (origin);