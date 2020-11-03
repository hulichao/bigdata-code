dt=$(date "+%Y%m%d")
hive -e \
"load data local inpath '/user_clicks/$dt' overwrite into table mydb.user_clicks partition(dt=$dt)"