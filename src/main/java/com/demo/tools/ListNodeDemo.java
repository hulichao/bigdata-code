package com.demo.tools;

import java.util.HashMap;
import java.util.Map;

class ListNode {
    Integer val ;
    ListNode next;
    public ListNode(int data) {
        this.val = data;
    }
}
public class ListNodeDemo {
    public static void main(String[] args) {
        ListNode ans = new ListNode(-1);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(1);
        ListNode a3 = new ListNode(1);
        ListNode a4 = new ListNode(2);
        ListNode a5 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;


        ListNode t = deleteDuplicates(a1);
        while (t!= null) {
            System.out.println(t.val);
            t = t.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        ListNode cur = head;
        while (cur != null) {
            map.put(cur.val, map.getOrDefault(cur.val, 0) + 1);
            cur = cur.next;
        }

        //头节点不固定，可能被移除，虚拟头节点
        ListNode ans = new ListNode(-1);
        ListNode du = ans;
        cur = head;
        while (cur != null) {
            if (map.get(cur.val) == 1) {
                du.next = cur;
                du = du.next;
            }
            cur = cur.next;


        }

        du.next = null;

        return ans.next;
    }
}
