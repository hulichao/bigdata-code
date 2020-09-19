package com.hoult.mr.job;

import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerMapper extends Reducer<Text, LongWritable, LongWritable, NullWritable> {

    private Long index = 0L;

    //对一个reduce task 中的values 进行排序

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        for (LongWritable value: values)
            context.write(value, NullWritable.get());
    }
}
