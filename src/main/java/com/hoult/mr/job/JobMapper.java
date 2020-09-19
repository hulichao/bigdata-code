package com.hoult.mr.job;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class JobMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value,
                       Context context) throws IOException, InterruptedException {
        IntWritable intWritable = new IntWritable(Integer.parseInt(value.toString()));
        context.write(intWritable, intWritable);
    }
}
