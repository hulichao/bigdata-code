package com.hoult.mr.wordcount.output;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author hulichao
 * @date 20-9-17
 **/
public class CustomWriter extends RecordWriter<Text, NullWritable> {

    //定义成员变量
    private FSDataOutputStream googleOutput;
    private FSDataOutputStream otherOutput;

    public CustomWriter(FSDataOutputStream googleOutput, FSDataOutputStream otherOutput) {
        this.googleOutput = googleOutput;
        this.otherOutput = otherOutput;
    }

    //写出数据的逻辑
    @Override
    public void write(Text key, NullWritable nullWritable) throws IOException, InterruptedException {
        //写出数据，需要输出流
        // 判断是否包含“lagou”输出到不同文件
        if (key.toString().contains("google")) {
            googleOutput.write(key.toString().getBytes());
            googleOutput.write("\r\n".getBytes());
        } else {
            otherOutput.write(key.toString().getBytes());
            otherOutput.write("\r\n".getBytes());
        }
    }

    //关闭，释放资源
    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(googleOutput);
        IOUtils.closeStream(otherOutput);
    }
}
