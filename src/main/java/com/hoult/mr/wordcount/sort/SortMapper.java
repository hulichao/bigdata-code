package com.hoult.mr.wordcount.sort;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMapper extends Mapper<LongWritable, Text, SpeakBean, NullWritable> {
    final SpeakBean bean = new SpeakBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1 读取一行文本，转为字符串，切分
        final String[] fields = value.toString().split("\t");
        //2 解析出各个字段封装成SpeakBean对象
        bean.setDeviceId(fields[1]);
        bean.setSelfDrutation(Long.parseLong(fields[4]));
        bean.setThirdPartDuration(Long.parseLong(fields[5]));
        bean.setSumDuration(Long.parseLong(fields[6]));
        //3 SpeakBean作为key输出
        context.write(bean, NullWritable.get());
    }
}
