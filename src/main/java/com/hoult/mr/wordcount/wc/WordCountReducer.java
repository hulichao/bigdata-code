package com.hoult.mr.wordcount.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author hulichao
 * @date 20-9-14
 **/
//继承的Reducer类，要有四个泛型参数，2对kv
    //第一对kv:类型要与Mapper的输出类型一致
    //第二对kv:自己设计决定输出的类型，Text, IntWritable
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    //1.重写reduce办法
    //Text key: map 方法输出的keY,本案例就是单词
    //Iterable<IntWriable> values: 一组key对应的kv的value组成的集合
    /**
     * 假设map方法：hello:1 hello:1 hello:1
     * reduce的key和value是什么
     * key:hello,
     * values<1,1>
     */

//    2.遍历key对应的valuse累加结果

    final IntWritable total = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }

        total.set(sum);
        context.write(key, total);
//        super.reduce(key, values, context);
    }
}
