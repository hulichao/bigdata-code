package com.hoult.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author hulichao
 * @date 20-9-12
 **/
public class HdfsClient {

    // 1 获取文件系统
    Configuration configuration = new Configuration();
    FileSystem fs = null;
    @Before
    public void init() throws IOException, InterruptedException,
            URISyntaxException {
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://linux121:9000");
        // FileSystem fs = FileSystem.get(configuration);
        fs = FileSystem.get(new URI("hdfs://linux121:9000"),
                configuration, "root");
    }

    @After
    public void destroy() throws IOException {
        fs.close();
    }
    @Test
    public void testMkdirs() throws IOException, InterruptedException,
            URISyntaxException {
        // 2 创建目录
//        fs.mkdirs(new Path("/test"));
        // 3.删除目录
        fs.deleteOnExit(new Path("/tmp"));
        // 4 关闭资源
        fs.close();
    }

    @Test
    public void testPut() throws URISyntaxException, IOException, InterruptedException {
        //1.获取文件系统
        configuration.set("dfs.replication", "10");
        FileSystem fs = FileSystem.get(new URI("hdfs://linux121:9000"), configuration, "root");

        //2上传文件
        fs.copyFromLocalFile(new Path("/home/hulichao/input/wc.txt"), new Path("/wc.txt"));
        //3关闭资源
        fs.close();
    }

    @Test
    public void testCopyToLocalFile() throws IOException, InterruptedException,
            URISyntaxException{
    // 2 执行下载操作
    // boolean delSrc 指是否将原文件删除
    // Path src 指要下载的文件路径
    // Path dst 指将文件下载到的路径
    // boolean useRawLocalFileSystem 是否开启文件校验
        fs.copyToLocalFile(false, new Path("/wc.txt"), new
                Path("/home/hulichao/output/wc.txt"), true);
    // 3 关闭资源
        fs.close();
    }

    @Test
    public void testDelete() throws IOException, InterruptedException,
            URISyntaxException{
    // 2 执行删除
        fs.delete(new Path("/wc.txt"), true);
    // 3 关闭资源
        fs.close();
    }

    @Test
    public void testListFiles() throws IOException, InterruptedException,
            URISyntaxException{
        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"),
                true);
        while(listFiles.hasNext()){
            LocatedFileStatus status = listFiles.next();
        // 输出详情
        // 文件名称
            System.out.println(status.getPath().getName());
        // 长度
            System.out.println(status.getLen());
        // 权限
            System.out.println(status.getPermission());
        // 分组
            System.out.println(status.getGroup());
            short replication = status.getReplication();
            System.out.println("副本个数" + replication);
            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
        // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println("主机名称：" + host);
                }
            }
            System.out.println("-----------华丽的分割线----------");
        }
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testListStatus() throws IOException, InterruptedException,
            URISyntaxException{
    // 2 判断是文件还是文件夹
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
    // 如果是文件
            if (fileStatus.isFile()) {
                System.out.println("文件:"+fileStatus.getPath().getName());
            }else {
                System.out.println("文件夹:"+fileStatus.getPath().getName());
            }
        }
    // 3 关闭资源
        fs.close();
    }

    /**
     * 自定义IO流上传
     * 上传文件：准备输入流读取本地文件，使用hdfs的输出流写数据到hdfs
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void putFileToHDFS() throws IOException, InterruptedException,
            URISyntaxException {
        // 2 创建输入流
        FileInputStream fis = new FileInputStream(new File("/home/hulichao/input/wc.txt"));
        // 3 获取输出流
        FSDataOutputStream fos = fs.create(new Path("/wc2.txt"));
        // 4 流对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    // 文件下载
    @Test
    public void getFileFromHDFS() throws IOException, InterruptedException,
            URISyntaxException{
        // 1 获取文件系统
        // 2 获取输入流
        FSDataInputStream fis = fs.open(new Path("/wc.txt"));
        // 3 获取输出流
        FileOutputStream fos = new FileOutputStream(new
                File("/home/hulichao/output/wc.txt"));
        // 4 流的对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    @Test
    public void readFileSeek2() throws IOException, InterruptedException,
            URISyntaxException{
// 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://linux121:9000"),
                configuration, "root");
// 2 打开输入流,读取数据输出到控制台
        FSDataInputStream in = null;
        try{
            in= fs.open(new Path("/wc.txt"));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0); //从头再次读取
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }

    //演示从本地文件系统上传到hdfs的过程
    @Test
    public void testUploadPacket() throws IOException {
        //1.准备读取本地文件的输入流
        FileInputStream in = new FileInputStream(new File("/home/hulichao/input/wc.txt"));
        //2.准备好写出数据到hdfs的输出流
        FSDataOutputStream out = fs.create(new Path("/wc.txt"), new Progressable() {
            @Override
            public void progress() {//这个progress方法就是每传输64kb（packet）就会执行一次
                System.out.println("&");
            }
        });
        //3.实现流拷贝
        IOUtils.copyBytes(in, out, configuration);//默认关闭流选项是true
        //4.关闭流
        fs.close();
    }
}
