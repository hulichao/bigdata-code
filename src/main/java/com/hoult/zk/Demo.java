package com.hoult.zk;

import org.I0Itec.zkclient.ZkClient;

public class Demo {
    public static void main(String[] args) {
        //先获取到zkClient对象，client与zk集群通信端口是2181
        final ZkClient zkClient = new ZkClient("linux121:8521");
        System.out.println("zkClient is ready");
        //创建节点
        zkClient.createPersistent("/zk-client/test/test2", true);
        System.out.println("path is create");

        //删除节点
        zkClient.deleteRecursive("/zk-client");
//        zkClient.delete("/zk-client");
        System.out.println("path is delete");
    }
}
