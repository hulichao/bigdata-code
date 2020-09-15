package com.hoult.hdfs;

import org.apache.hadoop.io.Text;

import java.util.StringTokenizer;

public class Demo {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("hello hdfs hello mr");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            System.out.println(word);
        }

    }
}
