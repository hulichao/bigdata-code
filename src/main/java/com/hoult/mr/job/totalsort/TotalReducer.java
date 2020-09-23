package com.hoult.mr.job.totalsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author hulichao
 * @date 20-9-20
 **/
public class TotalReducer extends Reducer<Text, IntWritable, IntWritable, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values,
                          Context context) throws IOException, InterruptedException {
        for (IntWritable value : values)
            context.write(value, NullWritable.get());
    }
}