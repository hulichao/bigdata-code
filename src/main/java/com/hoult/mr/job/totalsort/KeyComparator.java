package com.hoult.mr.job.totalsort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 自定义比较器来比较key的顺序
 * @author hulichao
 * @date 20-9-20
 **/
public class KeyComparator extends WritableComparator {
    protected KeyComparator() {
        super(Text.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        int num1 = Integer.valueOf(w1.toString());
        int num2 = Integer.valueOf(w2.toString());
        return num1 - num2;
    }
}