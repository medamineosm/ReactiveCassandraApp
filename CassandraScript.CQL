CREATE keyspace test WITH REPLICATION = {'class' : 'SimpleStrategy','replication_factor': 3 };
DROP TABLE webpage_by_datescan; CREATE TABLE webpage_by_datescan (
  clientid int,
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
  PRIMARY KEY ((clientid, accountid, website), datescan, url)
);

DROP TABLE webpage_by_status; CREATE TABLE webpage_by_status (
clientid int,
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
PRIMARY KEY ((clientid, accountid, website), datescan, status, url)
);

SELECT website,clientid,accountid,htmlhash,innerlinks,isorphan,isvalid,level,redirectedurl,status,url,datescan FROM  webpage_by_datescan;
SELECT website,clientid,accountid,htmlhash,innerlinks,isorphan,isvalid,level,redirectedurl,status,url,datescan FROM  webpage_by_statussele;
select url from webpage_by_status WHERE website='https://www.legrand.fr/' AND clientid=0 AND accountid='legrand' AND datescan='2018-12-19 23:00:00' AND status=200;

select count(*) from webpage_by_status WHERE website='https://www.legrand.fr/' AND clientid=0 AND accountid='legrand' AND datescan='2018-12-19 23:00:00' AND status=200;
select count(*) from webpage_by_status WHERE website='https://www.etam.com/' AND clientid=0 AND accountid='1-2-3' AND datescan='2018-12-20 23:00:00';
