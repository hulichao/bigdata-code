package com.hoult.mr.job;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class JobReducer  extends
        Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    private int index = 0;//全局排序计数器
    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values,
                          Context context) throws IOException, InterruptedException {
        for (IntWritable value : values)
            context.write(new IntWritable(++index), value);
    }
}