## 1.
SELECT team,
       count(*)
FROM
  (SELECT team,
          (YEAR - row_number() over(PARTITION BY team
                                    ORDER BY YEAR)) gid
   FROM t1) tmp
GROUP BY team,
         gid
HAVING count(*)>=3

## 2.
SELECT id,
       TIME,
       price,
       CASE
           WHEN (price > lag_price
                 AND price > lead_price) THEN '波峰'
           WHEN (price < lag_price
                 AND price < lead_price) THEN '波谷'
           ELSE 0
       END AS feature
FROM
  (SELECT id,
          TIME,
          price,
          lag(price) OVER (PARTITION BY id
                           ORDER BY TIME) lag_price,
                          lead(price) OVER (PARTITION BY id
                                            ORDER BY TIME) lead_price
   FROM t2) tmp
WHERE (price > lag_price
       AND price > lead_price)
  OR (price < lag_price
      AND price < lead_price)
ORDER BY id,
         TIME


## 3.1
## 1.使用lag()函数求出dt前一行的值，如果是第一行则使用本身，并且根据id分组 根据dt正序
## 2.将每一行dt与前一行dt转为时间戳进行相减，然后除以60转换为分钟数
## 3.使用casewhen 语句，大于30的则为1 小于30 的则为0
## 4.将结果作为子查询，然后在外面加一层查询，并且使用sum() 作为窗口函数，根据id分组，dt排序（当加了order by 后sum是计算的第一行到当前行的累加,以显示添加）
## 5.然后再去sum()每条记录的时长，，然后count()计算出步长，并且使用group by 以id 和前面的分组字段进行分组
SELECT id,
       (unix_timestamp(max(dt), 'yyyy/MM/dd HH:mm')- unix_timestamp(min(dt), 'yyyy/MM/dd HH:mm'))/60,
       count(1)
FROM t3
GROUP BY id

## 3.2
SELECT id,
       (unix_timestamp(max(dt), 'yyyy/MM/dd HH:mm')- unix_timestamp(min(dt), 'yyyy/MM/dd HH:mm'))/60 long_time,
       count(1)
FROM
  (SELECT id,
          sum(g) over(PARTITION BY id
                      ORDER BY dt ROWS BETWEEN UNBOUNDED preceding AND CURRENT ROW) AS gid,
          dt
   FROM
     (SELECT id,
             dt,
             CASE
                 WHEN ((unix_timestamp(dt, 'yyyy/MM/dd HH:mm') - unix_timestamp(lag(dt) OVER (PARTITION BY id
                                                                                              ORDER BY dt),'yyyy/MM/dd HH:mm'))/60) <=30 THEN 0
                 ELSE 1
             END g
      FROM t3) tmp) tmp1
GROUP BY id,
         gid
