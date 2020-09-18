package com.hoult.mr.job;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class JobReducer extends Reducer<Text, LongWritable, LongWritable, IntWritable> {

    private int rank = 0;
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values,
                          Context context) throws IOException, InterruptedException {
        for (LongWritable value : values)
            context.write(value, new IntWritable(++rank));
    }
}