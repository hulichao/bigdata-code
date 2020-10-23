## 需求
# input
A,2020-05-15 01:30:00
A,2020-05-15 01:35:00
A,2020-05-15 02:00:00
A,2020-05-15 03:00:10
A,2020-05-15 03:05:00
B,2020-05-15 02:03:00
B,2020-05-15 02:29:40
B,2020-05-15 04:00:00
对用户的日志数据打上会话内序号，如下

```txt
A,2020-05-15 01:30:00,1
A,2020-05-15 01:35:00,2
A,2020-05-15 02:00:00,3
A,2020-05-15 03:00:10,1
A,2020-05-15 03:05:00,2
B,2020-05-15 02:03:00,1
B,2020-05-15 02:29:40,2
B,2020-05-15 04:00:00,1
```

## 实现

在Hive中完成数据加载

```sql
--创建表
drop table if exists user_clicklog;
create table user_clicklog (
        user_id string,
        click_time string
        )
row format delimited fields terminated by ",";

--加载数据
load data local inpath '/root/impala_data/clicklog.dat' into table user_clicklog;
```

--使用Impala sql完成指标统计...
-- (与hive最后一题类似):
--1.上lag,日期函数 将小于30分钟的整为一组,
--2 上row_number排序函数
SELECT user_id,
       click_time,
       row_number() OVER (PARTITION BY user_id,
                                       gid
                          ORDER BY click_time)
FROM
  (SELECT user_id,
          sum(g) over(PARTITION BY user_id
                      ORDER BY click_time ROWS BETWEEN UNBOUNDED preceding AND CURRENT ROW) AS gid,
          click_time
   FROM
     (SELECT user_id,
             click_time,
             CASE
                 WHEN ((unix_timestamp(click_time, 'yyyy-MM-dd HH:mm:ss') - unix_timestamp(lag(click_time) OVER (PARTITION BY user_id
                                                                                                                 ORDER BY click_time),'yyyy-MM-dd HH:mm:ss'))/60) <=30 THEN 0
                 ELSE 1
             END g
      FROM user_clicklog) tmp) tmp1