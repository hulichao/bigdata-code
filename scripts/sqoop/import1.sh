# 导入全部数据
sqoop import \
--connect jdbc:mysql://hadoop-mysql:3306/sqoop \
--username root \
--password 123456 \
--table goodtbl \
--target-dir /root/hoult/1 \
--delete-target-dir \
--num-mappers 1 \
--fields-terminated-by "\t"