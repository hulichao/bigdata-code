package com.demo;

//对给定整型n, 开启n个线程（编号分别为1到n）。
//线程的工作逻辑为：编号为x的线程只能打印整数x，
//实现代码逻辑，使得n个线程协同工作按顺序打印自然数列：1, 2, 3, ..., n。
public class ThreadDemo {
    //当前正在执行任务，全局可见
    public static volatile char now;

    public static void main(String[] args) {
        //输入任务
        char[] input = {'1','2','3','4','5'};
        //每个线程要执行任务数
        final int n = 1;
        for (int j = 0; j < input.length; j++) {
            final int t = j;
            //开启线程任务
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < n; i++) {
                        //循环等待，自旋
                        while (now != input[t]) {}
                        System.out.print(input[t]);//处理任务
                        //修改当前执行任务的全局状态
                        if (t + 1 < input.length)
                            now = input[t + 1];
                        else
                            now = input[0];
                    }
                }
            });
            thread.start();
        }
        //边界
        now = input[0];
    }
}