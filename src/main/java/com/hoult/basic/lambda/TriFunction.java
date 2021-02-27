package com.hoult.basic.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TriFunction {
    public static void main(String[] args) {
        String a = String.valueOf(null);
        System.out.println(a);
    }

    public static int add(int x,int y){return x+y;}
//    public static void test(){
//        int s=add(2,3);
//        Function<Integer, Function<Integer, Integer>>  curryAdder = x -> y -> x + y;
//        s= curryAdder.apply(2).apply(3);
//        TernaryFunction<String, String,Integer,String> te =(x,y,z)->x+y+(" "+z);
//        String s =te.apply("y", " qj",2065);
//        BiFunction<String, String,String> bi =te.get(123);
//        s =bi.apply("y", " qj");;
//
//        TernaryFunction t = TriFunction::booToString;
//    }


    public static Integer booToString(Boolean a, Integer b, Integer c, Integer d) {
        if (a)
            return b;
        else return c;
    }

    public interface TernaryFunction<T,U,V,R>{
        R apply(T t,U u,V v);

        default public BiFunction<T,U,R> get(V v){
            return (t,u)->this.apply(t,u,v);
        }
    }

}
