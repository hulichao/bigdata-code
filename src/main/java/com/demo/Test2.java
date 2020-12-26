package com.demo;

import java.util.Random;

public class Test2 {

    static Random random = new Random();
    public static void main(String[] args) {
        long[] result = generate(100, 10, 90, 1);
        for (long l : result) {
            System.out.println(l);
        }
    }

    static long xRandom(long min, long max) {
        return sqrt(nextLong(sqr(max - min)));
    }

    public static long[] generate(long total, int count, long max, long min) {
        long[] result = new long[count];

        long average = total / count;

        for (int i = 0; i < result.length; i++) {
            //平均 -
            if (nextLong(min, max) > average) {
                long temp = min + xRandom(min, average);
                result[i] = temp;
                total -= temp;
            } else {
                // 平均 +
                long temp = max - xRandom(average, max);
                result[i] = temp;
                total -= temp;
            }
        }
        // 余钱
        while (total > 0) {
            for (int i = 0; i < result.length; i++) {
                if (total > 0 && result[i] < max) {
                    result[i]++;
                    total--;
                }
            }
        }
        // 负钱
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

    static long sqrt(long n) {
        return (long) Math.sqrt(n);
    }

    static long sqr(long n) {
        return n * n;
    }

    static long nextLong(long n) {
        return random.nextInt((int) n);
    }

    static long nextLong(long min, long max) {
        return random.nextInt((int) (max - min + 1)) + min;
    }

}
