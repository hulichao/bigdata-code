package com.hoult.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class nvl extends UDF {
    public Text evaluate(final Text t, final Text x) {
        if (t == null || t.toString().trim().length()==0) {
            return x;
        }
        return t; }
}
