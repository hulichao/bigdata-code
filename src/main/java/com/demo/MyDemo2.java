package com.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MyDemo2 {
    public static void main(String[] args) {
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<Map.Entry<Integer, Integer>>((x, y) -> y.getValue() - x.getValue());
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        findShortestSubArray(new int[]{1,2,2,3,1, 4, 2});

    }

    public static int findShortestSubArray(int[] nums) {

        //每个元素的开始位置
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        //每个元素的度
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map1.put(nums[i], map1.getOrDefault(nums[i], i));
            map2.put(nums[i], map2.getOrDefault(nums[i], 0) + 1);
        }

        map1.entrySet().forEach(System.out::println);

        //第二次遍历
        int ma = Integer.MIN_VALUE;
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            ma = Math.max(ma, map2.get(nums[i]));
            if (ma == map2.get(nums[i]) && i != map1.get(nums[i]))
                len = Math.min(len, i + 1 - map1.get(nums[i]));

            System.out.println(ma + ":" + len);
        }

        return len;
    }
}
