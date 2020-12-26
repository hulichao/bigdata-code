package com.demo;

public class Test {
    //当前
    public static volatile char now;

    public static void main(String[] args) {
        char[] input = {'1','2','3','4','5'};//输入任务

        final int n = 1;

        for (int j = 0; j < input.length; j++) {
            final int t = j;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < n; i++) {
                        while (now != input[t]) {}//循环等待
                        System.out.print(input[t]);//处理任务
                        if (t + 1 < input.length)
                            now = input[t + 1];
                        else
                            now = input[0];
                    }
                }
            });
            thread.start();
        }
        now = input[0];
    }
}
