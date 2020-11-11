package com.hoult.redis;

import org.junit.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import redis.clients.jedis.Jedis;

public class TestRedis {
    @Test
    public void testConn2() {
        Jedis jedis = new Jedis("centos", Integer.parseInt("7001"));
        //在Redis中写字符串 key value
        jedis.set("jedis:name:1", "jd-zhangfei");
        //获得Redis中字符串的值
        System.out.println(jedis.get("jedis:name:1"));
        //在Redis中写list
        jedis.lpush("jedis:list:1", "1", "2", "3", "4", "5");
        //获得list的长度
        System.out.println(jedis.llen("jedis:list:1"));
    }
}
