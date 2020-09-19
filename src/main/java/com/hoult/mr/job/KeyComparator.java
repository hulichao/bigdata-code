package com.hoult.mr.job;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class KeyComparator extends WritableComparator {
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Long a1 = Long.valueOf(a.toString());
        Long b1 = Long.valueOf(b.toString());
        System.out.println(a1 + ":" + b1);
        return a1.compareTo(b1);
    }


}
