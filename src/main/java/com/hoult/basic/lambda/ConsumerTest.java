package com.hoult.basic.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        List noNeedRecountVars = new ArrayList<String>();
        String canUseVars = "";
        Arrays.stream(canUseVars.split(",")).filter(var -> !var.contains("null")).map(var -> var.trim()).forEach(var -> noNeedRecountVars.add(var));
        noNeedRecountVars.stream().forEach(System.out::println);
    }
}
