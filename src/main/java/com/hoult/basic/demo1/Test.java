package com.hoult.basic.demo1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
//        System.out.println(getImptVariables());
//        System.out.println(test());
    }

    private static Map<String, String> getImptVariables() {
        String[] sts = new String[] {"a", "c", "e"};
        Stream<String> keys = Arrays.stream(sts).flatMap(line -> Arrays.asList(line.split("_")).stream());
        return keys.collect(Collectors.toMap(String::toString, Function.identity()));
    }

    private static boolean test() {
        String value1 = "1.00";
        String value2 = "1.1";
        return Double.valueOf(value1).compareTo(Double.valueOf(value2)) == 0;
    }
}
