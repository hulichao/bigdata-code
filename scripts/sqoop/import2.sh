# 导入查询数据
sqoop import \
--connect jdbc:mysql://hadoop-mysql:3306/sqoop \
--username root \
--password 123456 \
--target-dir /root/hoult/3 \
--append \
-m 1 \
--fields-terminated-by "\t" \
--query 'select gname, serialNumber, price, stock_number,
create_time from goodtbl where price>88 and $CONDITIONS;'