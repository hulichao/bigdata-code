package com.demo;

import java.util.HashMap;
import java.util.Map;

public class MyDemo3 {
    public static void main(String[] args) {
        lengthOfLongestSubstring("abba")
    }

    public static int lengthOfLongestSubstring(String s) {
        //用hash表记录当前遇到的字母的最近一次位置 ，如果有重复key，说明要更新当前窗口的左边界了

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        int result = 0;
        int start = 0;
        for (int end=0; end < s.length(); end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(map.get(s.charAt(end)) + 1, start);
                map.put(s.charAt(end), end);
            } else {
                map.put(s.charAt(end), end);
            }

            result = Math.max(result, end - start + 1);

        }

        return result;
    }
}
