package com.hoult.mr.wordcount.speak;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author hulichao
 * @date 20-9-14
 **/
public class SpeakMapper extends Mapper<LongWritable, Text, Text,
        SpeakBean> {
    Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
    // 1 获取一行
        String line = value.toString();
    // 2 切割字段
        String[] fields = line.split("\t");
        String out = StringUtils.join(fields, ",");
        System.out.println(out);
        // 3 封装对象
    // 取出设备id
        String deviceId = fields[1];
    // 取出自有和第三方时长数据
        long selfDuration = Long.parseLong(fields[fields.length - 3]);
        long thirdPartDuration = Long.parseLong(fields[fields.length - 2]);
        k.set(deviceId);
        SpeakBean v = new SpeakBean(selfDuration, thirdPartDuration, deviceId);
        // 4 写出
        context.write(k, v);
    }
}