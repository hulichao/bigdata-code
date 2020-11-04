package com.hoult.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HbaseClient {
    Configuration conf=null;
    Connection conn=null;
    HBaseAdmin admin =null;
    @Before
    public void init () throws IOException {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","linux121,linux122");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conn = ConnectionFactory.createConnection(conf);
    }

    @After
    public void destroy(){
        if(admin!=null){
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(conn !=null){
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //创建表
    @Test
    public void createTable() throws IOException {
        admin = (HBaseAdmin) conn.getAdmin();
        //创建表描述器器
        HTableDescriptor teacher = new HTableDescriptor(TableName.valueOf("teacher"));
        //设置列列族描述器器
        teacher.addFamily(new HColumnDescriptor("info"));
        //执⾏行行创建操作
        admin.createTable(teacher);
        System.out.println("teacher表创建成功！！");
    }

    //插⼊入⼀一条数据
    @Test
    public void putData() throws IOException {
    //获取⼀一个表对象
        Table t = conn.getTable(TableName.valueOf("teacher"));
    //设定rowkey
        Put put = new Put(Bytes.toBytes("110"));
    //列列族，列列，value
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("addr"), Bytes.toBytes("beijing"));
    //执⾏行行插⼊入
        t.put(put);
    // t.put();//可以传⼊入list批量量插⼊入数据
        //关闭table对象
        t.close();
        System.out.println("插⼊入成功！！");
    }
}
