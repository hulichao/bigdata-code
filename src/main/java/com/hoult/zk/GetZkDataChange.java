package com.hoult.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * 使用监听器监听节点数据的变化
 */
public class GetZkDataChange {
    public static void main(String[] args) throws InterruptedException {
        //获取zkclient对象
        final ZkClient zkClient = new ZkClient("linux121:8521");
        //内置的序列化有问题，需要使用自定义的序列化器
        zkClient.setZkSerializer(new ZkStrSerialize());
        //判断节点是否存在，不存在创建节点并赋值
        if (!zkClient.exists("/hoult-zk")) {
            zkClient.createEphemeral("/hoult-zk");
        }
        //注册监听器，节点数据改变，接受通知后处理逻辑定义
        zkClient.subscribeDataChanges("/hoult-zk", new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                //处理数据改变
                System.out.println(path + " data is chaneged ,new data is  " + data);
            }

            @Override
            public void handleDataDeleted(String data) throws Exception {
                    //处理数据改变
                System.out.println(" data is deleted");
            }
        });
        //更新节点的数据，删除节点，验证监听器是否正常运行
        Object o = zkClient.readData("/hoult-zk");
        System.out.println(o);
        zkClient.writeData("/hoult-zk", "new data");
        Thread.sleep(1000);
        zkClient.deleteRecursive("/hoult-zk");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
