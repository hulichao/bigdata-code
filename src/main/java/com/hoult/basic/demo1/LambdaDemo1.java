package com.hoult.basic.demo1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * (o1, o2) -> Integer.compare(o1, o2)
 * -> ：lambda操作符，箭头操作符
 * -> 左边：lambda形参列表（接口中的形参列表）
 * ->右边：lambda体（其实就是重写的抽象方法的抽象体）
 * 本质：作为接口的具体实例
 * 使用六种情况：
 * 如果一个接口中，只声明了一个抽象方法，则称此接口为函数式接口
 */
public class LambdaDemo1 {
    //语法格式1，无参，无返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("my love");
            }
        };

        r1.run();

        System.out.println("*****************");
        Runnable r2 = () -> System.out.println("i love tiangong");
        r2.run();;
    }

    //语法格式2，有参数，无返回值
    @Test
    public void test2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        con.accept("谎言和誓言的区别");

        System.out.println("********************");
        Consumer<String> con1 = (String s) -> {System.out.println(s);};
        con1.accept("一个是听的人当真人，一个是说的人当真");
    }

    //语法格式3： 数据类型可以省略，因为由编译器推断得出，称为"类型推断"
    @Test
    public void test3(){
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        con1.accept("谎言和誓言的区别");

        System.out.println("********************");
        Consumer<String> con2 = (s) -> {System.out.println(s);};
        con2.accept("一个是听的人当真人，一个是说的人当真");
    }

    @Test
    public void test4(){
        ArrayList<String> list = new ArrayList<>();
    }

    //语法格式4： 若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test5(){
        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        con1.accept("谎言和誓言的区别");

        System.out.println("********************");
        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("一个是听的人当真人，一个是说的人当真");
    }

    //语法格式5： lambda 需要两个以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test6(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };

        System.out.println("**************");

        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };

        System.out.println(com2.compare(1, 1));
    }

    //语法格式6：当lambda体只有一条执行语句时，return 与 大括号可以省略
    @Test
    public void test7(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(1, 1));

        System.out.println("**************");

        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);

        System.out.println(com2.compare(12, 1));
    }

    @Test
    public void test8(){
        Consumer<String> con1 = s -> {
            System.out.println(s);
        };

        con1.accept("谎言和誓言的区别");

        System.out.println("********************");
        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("一个是听的人当真人，一个是说的人当真");
    }
}
