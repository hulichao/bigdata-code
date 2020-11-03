userId   click_time             index
uid1	2020-06-21	12:10:10	a.html
uid2	2020-06-21	12:15:10	b.html
uid1	2020-06-21	13:10:10	c.html
uid1	2020-06-21	15:10:10	d.html
uid2	2020-06-21	18:10:10	e.html
用户点击行为数据，三个字段是用户id,点击时间，访问页面

hdfs目录会以日期划分文件，例如：

/user_clicks/20200621/clicklog.dat
/user_clicks/20200622/clicklog.dat
/user_clicks/20200623/clicklog.dat
...
Hive表

原始数据分区表

create table user_clicks(id string,click_time string, index string)
partitioned by(dt string) row format delimited fields terminated by '\t' ;
需要开发一个import.job每日从hdfs对应日期目录下同步数据到该表指定分区。（日期格式同上或者自定义）

dt=$(date "+%Y%m%d")
hive -e \
"load data local inpath '/user_clicks/$dt/clicklog.dat' overwrite into table mydb.user_clicks partition(dt=$dt)"
指标表

create table user_info(active_num string,dt string) row format delimited fields terminated by '\t' ;
需要开发一个analysis.job依赖import.job执行，统计出每日活跃用户(一个用户出现多次算作一次)数并插入user_inof表中。
