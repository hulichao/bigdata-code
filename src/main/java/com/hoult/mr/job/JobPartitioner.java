package com.hoult.mr.job;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author hulichao
 * @date 20-9-20
 **/
public class JobPartitioner extends Partitioner<IntWritable, IntWritable> {
    @Override
    public int getPartition(IntWritable key, IntWritable value, int numPartitions) {
        int keyValue = Integer.parseInt(key.toString());

        for (int i = 0; i < 10; i++) {
            if (keyValue < 1000 * (i+1) && keyValue >= 1000 * (i-1)) {
                System.out.println("key:" + keyValue + ", part:" + i);
                return i;
            }
        }

        return 10;
    }
}