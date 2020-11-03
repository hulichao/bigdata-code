dt=$(date "+%Y%m%d")
hive -e "insert overwrite table mydb.user_info select count(distinct id) as active_num,$dt from mydb.user_clicks"