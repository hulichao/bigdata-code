package com.hoult.scala.java;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello");
        new C();
    }
}

class C {
    { System.out.println("hello...C");}
}
