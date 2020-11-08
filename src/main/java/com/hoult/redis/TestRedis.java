package com.hoult.redis;

import org.junit.Test;

public class TestRedis {
    @Test
    public void testConn2() {
        Redis jedis = new Jedis("centos", "6379");
    }
}
