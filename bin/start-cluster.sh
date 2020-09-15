## 集群群起脚本,需要登录linux121
## linux121启动hdfs集群，包括nn 2nn dnm historyserver
ssh root@linux121 "cd /opt/hoult/servers/hadoop-2.9.2 && bin/hadoop namenode -format"
ssh root@linux121 "cd /opt/hoult/servers/hadoop-2.9.2 && sbin/start-dfs.sh && sbin/mr-jobhistory-daemon.sh start historyserver"

## linux123启动yarn集群
ssh root@linux123 "cd /opt/hoult/servers/hadoop-2.9.2 && sbin/start-yarn.sh"

