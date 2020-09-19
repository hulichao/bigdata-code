package com.hoult.mr.wordcount.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author hulichao
 * @date 20-9-14
 **/

//1.继承mapperr
    //2.Mapper类的泛型参数：共4个，两对kv
    //2.1 第一对kv:map输入参数类型
    //2.2 第二对kv:map输出类型
    //LongWritable, Text --> 文本偏移量，一行文本内容
    //Text,IntWritable-->单词，1
public class WordCountMapper extends Mapper {

    //3.重写mapper类的方法
    /**
     * 1.接受文本内容，转为String类型
     * 2.按照空格进行且分
     * 3.输出《单词：1》
     */
    final Text word = new Text();
    final IntWritable one = new IntWritable(1);
    //LongWritable Text-->文本偏移量，一行文本内容，map方法的输入参数，一行文本就调用一次map方法
    @Override
    protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
//        * 1.接受文本内容，转为String类型
        final String str = value.toString();
//        * 2.按照空格进行且分
        final String[] words = str.split("\\s+");
//        * 3.输出《单词：1》//
        for (String s : words) {
            word.set(s);
            context.write(word, one);
        }
//        super.map(key, value, context);
    }
}
