package com.hoult.mr.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JobDriver extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapreduce.totalorderpartitioner.naturalorder", "false");
        Job job = Job.getInstance(conf, "configure Sort app");
        job.setJarByClass(JobDriver.class);

        //设置读取文件的路径，都是从HDFS中读取。读取文件路径从脚本文件中传进来
        FileInputFormat.addInputPath(job,new Path(args[0]));
//        FileInputFormat.addInputPath(job,new Path("/home/hulichao/input"));
        //设置mapreduce程序的输出路径，MapReduce的结果都是输入到文件中
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
//        FileOutputFormat.setOutputPath(job,new Path("/home/hulichao/output"));
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        //设置比较器，用于比较数据的大小，然后按顺序排序，该例子主要用于比较两个key的大小
        job.setSortComparatorClass(KeyComparator.class);
        job.setNumReduceTasks(1);//设置reduce数量

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(IntWritable.class);

        //设置保存partitions文件的路径
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), new Path(args[2]));
        //key值采样，0.01是采样率，
        InputSampler.Sampler<Text, Text> sampler = new InputSampler.RandomSampler<>(0.01, 1000, 100);
        //将采样数据写入到分区文件中
        InputSampler.writePartitionFile(job, sampler);

        job.setMapperClass(JobMapper.class);
        job.setReducerClass(JobReducer.class);
        //设置分区类。
        job.setPartitionerClass(TotalOrderPartitioner.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }
    public static void main(String[] args)throws Exception{

        int exitCode = ToolRunner.run(new JobDriver(), new String[] {"/Users/hulichao/input", "/Users/hulichao/output", "/Users/hulichao/output2"});
        System.exit(exitCode);
    }
}
