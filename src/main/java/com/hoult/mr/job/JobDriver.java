package com.hoult.mr.job;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JobDriver extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("input-path output-path");
            System.exit(1);
        }

        Job job = Job.getInstance(getConf());
        job.setJarByClass(JobDriver.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(JobMapper.class);

        //设置自定义分区器,输出多文件全局排序时使用
//        job.setPartitionerClass(JobPartitioner.class);
        job.setReducerClass(JobReducer.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(NullWritable.class);
        //使用一个reduce来排序
        job.setNumReduceTasks(1);
//        job.setNumReduceTasks(10);
        job.setJobName("JobDriver");
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args)throws Exception{

//        int exitCode = ToolRunner.run(new JobDriver(), args);
        int exitCode = ToolRunner.run(new JobDriver(), new String[] {"data/job/input", "data/job/output"});
        System.exit(exitCode);
    }
}
