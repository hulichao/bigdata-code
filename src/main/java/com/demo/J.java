package com.demo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] dailyTemperatures(int[] T) {
        //单调栈
        if (T == null)
            return null;

        int[] result = new int[T.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < T.length; i++) {

            if (stack.peek() >= T[i]) {
                stack.push(i);
                continue;
            }

            int tmp = 1;
            while (!stack.isEmpty() && stack.peek() < T[i]) {
                Integer cur = stack.pop();
                result[cur] = tmp++;
                stack.push(i);
            }

        }
        return result;
    }

}
public class J {
    public static void main(String[] args) {
        int[] ints = new Solution().dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


}
