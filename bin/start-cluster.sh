## linux121启动namenode -format
#namenode -format
## linux121启动hdfs集群，包括nn 2nn dn
ssh root@linux121 "/bin/sh start-dfs.sh"
## linux123启动yarn集群
start-yarn.sh
## linux121 JobHistoryServer启动
mr-jobhistory-daemon.sh start historyserver