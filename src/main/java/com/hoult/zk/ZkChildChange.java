package com.hoult.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ZkChildChange {
    public static void main(String[] args) throws InterruptedException {
        //获取到zkClient
        final ZkClient zkClient = new ZkClient("linux121:8521");

        //zkClient对指定目录进行监听（不存在目录 /hoult-client），指定收到通知之后的逻辑
        //对/hoult-client注册了监听器
        //该方法是接受到通知之后的执行逻辑
        zkClient.subscribeChildChanges("/hoult-zk", (path, childs) -> {
            //答应节点信息
            System.out.println(path + " 's changes is: " + StringUtils.join(childs, ","));
        });

        //使用zkClient创建节点，删除节点，来验证监听器是否运行
//        zkClient.createPersistent("/hoult-zk");
//        Thread.sleep(1000);
//        zkClient.createPersistent("/hoult-zk/c1/c5", true);
//        zkClient.createPersistent("/hoult-zk/c2");
//        Thread.sleep(1000);
//        zkClient.deleteRecursive("/hoult-zk/c1");
//        Thread.sleep(1000);

        zkClient.deleteRecursive("/hoult-zk");
        Thread.sleep(Integer.MAX_VALUE);
        /**
         * 1.监听目录可以对没有的目录监听
         * 2.监听目录下子节点发生改变，可以接收到通知，携带数据与子节点列表
         * 3.监听目录创建和删除本身也会被监听到
         */
    }
}
