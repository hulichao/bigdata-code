package com.demo;

import java.util.Random;

public class RedBagDemo {

    //随机数种子
    static Random random = new Random();

    public static void main(String[] args) {

        long[] result = generate(100, 10, 90, 1);
        for (long l : result) {
            System.out.println(l);
        }

    }

    //放大取随机再缩小
    static long xRandom(long min, long max) {
        return sqrt(nextLong(sqr(max - min)));
    }

    //红包总额度，人数，红包最大金额，红包最小金额

    public static long[] generate(long total, int count, long max, long min) {

        long[] result = new long[count];

        //取平均值，从平均值上加减
        long average = total / count;

        for (int i = 0; i < result.length; i++) {
            //红包大了，往平均线上减
            if (nextLong(min, max) > average) {
                long temp = min + xRandom(min, average);
                result[i] = temp;
                total -= temp;
            } else {
                //红包小了，往平均线上减
                long temp = max - xRandom(average, max);
                result[i] = temp;
                total -= temp;
            }
        }

        // 余钱，给不超过最大额的人每个都加一块
        while (total > 0) {
            for (int i = 0; i < result.length; i++) {
                if (total > 0 && result[i] < max) {
                    result[i]++;
                    total--;
                }
            }
        }

        // 如果总额小于0，按人头减1
        while (total < 0) {
            for (int i = 0; i < result.length; i++) {
                if (total < 0 && result[i] > min) {
                    result[i]--;
                    total++;
                }
            }
        }
        return result;
    }

    //缩小
    static long sqrt(long n) {
        return (long) Math.sqrt(n);
    }

    //放大
    static long sqr(long n) {
        return n * n;
    }

    //下一个不超过n的随机数
    static long nextLong(long n) {
        return random.nextInt((int) n);
    }
    //下一个在min 和 max间的随机数

    static long nextLong(long min, long max) {
        return random.nextInt((int) (max - min + 1)) + min;
    }

}