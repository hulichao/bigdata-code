package com.demo;

/**
 * @author hulichao
 * @date 2021/11/20
 **/
public class Outer {
    static class Inner {
        private void f() {
            System.out.println("f()");
        }
    }

    public static void main(String[] args) {
        new Inner().f();
    }
}
