package com.hoult.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;

public class JedisClusterDemo {
    public static void main(String[] args) throws IOException {
        JedisPoolConfig config = new JedisPoolConfig();
        HashSet<HostAndPort> jedisClusterNodes = new HashSet<>();

        //添加集群节点
        jedisClusterNodes.add(new HostAndPort("centos",7001));
        jedisClusterNodes.add(new HostAndPort("centos",7002));
        jedisClusterNodes.add(new HostAndPort("centos",7003));
        jedisClusterNodes.add(new HostAndPort("centos",7004));
        jedisClusterNodes.add(new HostAndPort("centos",7005));
        jedisClusterNodes.add(new HostAndPort("centos",7006));
        jedisClusterNodes.add(new HostAndPort("centos",7007));
        jedisClusterNodes.add(new HostAndPort("centos",7008));

        //获取集群连接
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, config);

        //设置值
        jedisCluster.set("name:001","zhangfei");

        //获取值
        String value = jedisCluster.get("name:001");
        System.out.println(value);

        //关闭连接
        jedisCluster.close();
    }

}
