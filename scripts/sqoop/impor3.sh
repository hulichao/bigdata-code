# 导入查询数据(使用关键字)
sqoop import \
--connect jdbc:mysql://hadoop-mysql:3306/sqoop \
--username root \
--password 123456 \
--target-dir /root/hoult \
--delete-target-dir \
-m 1 \
--fields-terminated-by "\t" \
--table goodtbl \
--where "price>=68"