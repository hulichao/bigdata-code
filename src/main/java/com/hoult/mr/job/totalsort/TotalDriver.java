package com.hoult.mr.job.totalsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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

/**
 * @author hulichao
 * @date 20-9-20
 **/
public class TotalDriver extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //设置非分区排序
        conf.set("mapreduce.totalorderpartitioner.naturalorder", "false");
        Job job = Job.getInstance(conf, "Total Driver");
        job.setJarByClass(TotalDriver.class);

        //设置读取文件的路径，都是从HDFS中读取。读取文件路径从脚本文件中传进来
        FileInputFormat.addInputPath(job,new Path(args[0]));
        //设置mapreduce程序的输出路径，MapReduce的结果都是输入到文件中
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        //设置比较器，用于比较数据的大小，然后按顺序排序，该例子主要用于比较两个key的大小
        job.setSortComparatorClass(KeyComparator.class);
        job.setNumReduceTasks(10);//设置reduce数量

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(NullWritable.class);

        //设置保存partitions文件的路径
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), new Path(args[2]));
        //key值采样，0.01是采样率，
        InputSampler.Sampler<Text, Text> sampler = new InputSampler.RandomSampler<>(0.1, 3, 100);
        //将采样数据写入到分区文件中
        InputSampler.writePartitionFile(job, sampler);

        job.setMapperClass(TotalMapper.class);
        job.setReducerClass(TotalReducer.class);
        //设置分区类。
        job.setPartitionerClass(TotalOrderPartitioner.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }
    public static void main(String[] args)throws Exception{
//        int exitCode = ToolRunner.run(new TotalDriver(), new String[] {"data/job/input", "data/job/output", "data/job/partition","data/job/partitio2"});
        int exitCode = ToolRunner.run(new TotalDriver(), args);
        System.exit(exitCode);
    }
}
