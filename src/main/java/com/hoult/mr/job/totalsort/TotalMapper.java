package com.hoult.mr.job.totalsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author hulichao
 * @date 20-9-20
 **/
public class TotalMapper extends Mapper<Text, Text, Text, IntWritable> {
    @Override
    protected void map(Text key, Text value,
                       Context context) throws IOException, InterruptedException {
        System.out.println("key:" + key.toString() + ", value:" + value.toString());
        context.write(key, new IntWritable(Integer.parseInt(key.toString())));
    }
}